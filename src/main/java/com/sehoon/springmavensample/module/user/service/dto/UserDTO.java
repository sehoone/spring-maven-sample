package com.sehoon.springmavensample.module.user.service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {

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
    private String employNo;
    private String enterCardNo;
}
