package com.sehoon.springmavensample.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

import lombok.Data;

/**
 * 커스텀 application properties
 */
@Data
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    private CorsConfiguration cors = new CorsConfiguration();

    private String jwtBase64Secret;
    private long tokenValidityInSeconds;
    private long tokenValidityInSecondsForRememberMe;

    private String contentSecurityPolicy;
    private String serverDomain;
}
