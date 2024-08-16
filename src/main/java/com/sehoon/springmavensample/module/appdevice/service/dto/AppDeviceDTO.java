package com.sehoon.springmavensample.module.appdevice.service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AppDeviceDTO {

    private Long id;                    //식별키
    private Long userId;                // 사용자id
    private String uuid;                // uuid
    private String osCode;              // os코드
    private String appVersion;          // app버젼
    private String deviceInfo;          // 기기정보
}
