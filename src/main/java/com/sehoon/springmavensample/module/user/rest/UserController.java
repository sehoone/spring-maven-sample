package com.sehoon.springmavensample.module.user.rest;

import java.time.Instant;

import javax.validation.constraints.NotBlank;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sehoon.springmavensample.common.config.ApplicationProperties;
import com.sehoon.springmavensample.common.exception.ApiException;
import com.sehoon.springmavensample.common.res.ApiResponse;
import com.sehoon.springmavensample.common.res.ResultCode;
import com.sehoon.springmavensample.common.security.jwt.JwtUtils;
import com.sehoon.springmavensample.common.security.service.UserDetailsImpl;
import com.sehoon.springmavensample.module.user.rest.res.InitRes;
import com.sehoon.springmavensample.module.user.rest.res.MainRes;
import com.sehoon.springmavensample.module.user.service.UserService;
import com.sehoon.springmavensample.module.user.service.dto.UserDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * REST controller for managing
 * {@link net.openobject.o2pappserver.domain.User}.
 */
@Slf4j
@Tag(name = "UserController", description = "사용자 컨트롤러(앱초기, 사용자 정보 조회 등)")
@Validated
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    // private final AppVersionService appVersionService;
    private final UserService userService;
    private final ApplicationProperties applicationProperties;
    private final JwtUtils jwtUtils;

    /**
     * {@code GET  /init} : 초기구동
     *
     */
    @Operation(summary = "app초기조회(app실행)", description = "app실행시 호출. 버젼정보 유효성 체크<br> ")
    @GetMapping("/init")
    public ResponseEntity<ApiResponse<InitRes>> init(
            @Schema(description = "OS코드. A: Android, I: iOS", requiredMode = RequiredMode.REQUIRED, example = "I") @RequestParam @NotBlank @javax.validation.constraints.Pattern(regexp = "[A|I]") String osCode,
            @Schema(description = "APP버전", requiredMode = RequiredMode.REQUIRED, example = "0.0.1") @RequestParam("appVersion") @NotBlank String appVersion,
            @Schema(description = "uuid", requiredMode = RequiredMode.REQUIRED, example = "47F509CA-A797-45D8-8E21-3BD44F449343") @RequestParam("uuid") @NotBlank String uuid) {
        InitRes initRes = new InitRes();

        // 강업이 있는 가장 최신 앱버전 가져오기
        // boolean isForceUpdate = appVersionService.findForceUpdateVersionByOsCode(osCode)
        //         .map(AppVersionDTO::getVersion)
        //         .map(av -> appVersion != null && appVersion.compareTo(av) < 0)
        //         .orElse(false);

        // String regex = "^(?!.*itms-services:\\/\\/\\?action=download-manifest&url=).*";
        // Pattern pattern = Pattern.compile(regex);

        initRes.setBgImage(applicationProperties.getServerDomain() + "/background-image/mountains-1920-230329.jpg?"
            + Instant.now().toEpochMilli());

        return ResponseEntity.ok().body(ApiResponse.ok(initRes));
    }

    @Operation(summary = "메인", description = "메인")
    @GetMapping("/main")
    public ResponseEntity<ApiResponse<MainRes>> main() {
        UserDetailsImpl user = jwtUtils.getCurrentUserDetails().orElseThrow(() -> new ApiException(ResultCode.BAD_REQUEST));
        UserDTO userDTO = userService.findById(user.getId()).orElseThrow(() -> new ApiException(ResultCode.BAD_REQUEST));

        MainRes mainRes = MainRes.builder().name(userDTO.getName()).engName(userDTO.getEngName())
                .departmentName(userDTO.getDepartmentName())
                .positionName(userDTO.getPositionName()).profileUrl(userDTO.getProfileUrl()).build();
        return ResponseEntity.ok().body(ApiResponse.ok(mainRes));
    }
}
