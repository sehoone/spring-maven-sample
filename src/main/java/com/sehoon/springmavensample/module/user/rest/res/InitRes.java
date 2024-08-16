package com.sehoon.springmavensample.module.user.rest.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class InitRes {

    @Schema(description = "현재APP버젼", example = "1.3.1")
    private String curVersion;
    @Schema(description = "업데이트코드. 0:업데이트 없음. 1:선택업데이트, 2:강제업데이트", example = "0")
    private int updateCode;
    @Schema(description = "업데이트메세지", example = "XX 버그 수정")
    private String updateMsg;

    // private int pushStatus; // 0 미등록, 1 등록+동의, 2 미동의
    @Schema(description = "APP다운로드url(TODO 추가필요)", example = "http://naver.com/down/index.html")
    private String appDownUrl;
    @Schema(description = "배경이미지", example = "http://naver.com/bg.jpg?1679734888477")
    private String bgImage;

}
