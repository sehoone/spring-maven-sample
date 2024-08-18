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

    private String jwtBase64Secret; // JWT 비밀키
    private long tokenValidityInSeconds; // JWT 토큰 유효시간
    private long tokenValidityInSecondsForRememberMe; // JWT 토큰 리멤버미 유효시간

    private String contentSecurityPolicy; // 컨텐츠 보안 정책
    private String serverDomain; // 서버 도메인
}
