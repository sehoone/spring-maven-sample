package com.sehoon.springmavensample.module.user.rest.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MainRes {

    @Schema(description = "이름")
    private String name;
    @Schema(description = "영문명")
    private String engName;
    @Schema(description = "부서명")
    private String departmentName;
    @Schema(description = "직위명")
    private String positionName;
    @Schema(description = "프로필URL")
    private String profileUrl;
    @Schema(description = "팝업URL")
    private String popupUrl;

}
