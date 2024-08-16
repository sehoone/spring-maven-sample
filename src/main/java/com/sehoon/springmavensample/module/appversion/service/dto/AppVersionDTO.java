package com.sehoon.springmavensample.module.appversion.service.dto;

import lombok.Data;

@Data
public class AppVersionDTO {

    private Long id;
    private String osCode;
    private String version;
    private String fileDownUrl;
    private String description;
    private Boolean isForceUpdate;

}
