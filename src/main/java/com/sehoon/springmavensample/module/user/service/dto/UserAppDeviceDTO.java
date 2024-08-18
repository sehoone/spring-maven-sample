package com.sehoon.springmavensample.module.user.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserAppDeviceDTO {

    private Long id; // user device id
    private String account; // 계정
    private String password; // 비밀번호
    private String name; // 이름
    private String emailAddr; // 이메일
    private String phoneNumber; // 전화번호
    private String engName; // 영문명
    private String departmentName; // 부서명
    private String positionName; // 직위명
    private String profileUrl; // 프로필 URL
    
    private Long appDviceid; // app device id
    private String uuid; // uuid
    private String osCode; // os code
    private String appVersion; // 앱버젼
    private String deviceInfo; // 디바이스 정보
    private String pinAuthKey; // pin 인증키
    private String bioAuthKey; // bio 인증키
}
