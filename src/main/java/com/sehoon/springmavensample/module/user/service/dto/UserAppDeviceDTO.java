package com.sehoon.springmavensample.module.user.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserAppDeviceDTO {

    private Long id;
    private String account;
    private String password;
    private String name;
    private String emailAddr;
    private String phoneNumber;
    private String engName;
    private String departmentName;
    private String positionName;
    private String profileUrl;
    
    private Long appDviceid;
    private String uuid;
    private String osCode;
    private String appVersion;
    private String deviceInfo;
    private String pinAuthKey;
    private String bioAuthKey;
}
