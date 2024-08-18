package com.sehoon.springmavensample.module.user.service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {

    private Long id; // user id
    private String account; // 계정
    private String password; // 비밀번호
    private String name; // 이름
    private String emailAddr; // 이메일
    private String phoneNumber; // 전화번호
    private String engName; // 영문명
    private String departmentName; // 부서명
    private String positionName; // 직위명
    private String profileUrl; // 프로필 URL
    private String employNo; // 사번
    private String enterCardNo; // 입사카드번호
}
