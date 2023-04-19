package com.sehoon.springmavensample.common.config;

import org.springframework.context.annotation.Configuration;

/**
 * web 설정
 */
@Configuration
public class WebConfig {
	
	/**
	 * logging filter. 
	 * 로그에 노출할 정보를 설정한다
	 */
	// @Bean
	// public CommonsRequestLoggingFilter requestLoggingFilter() {
	// 	CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
	// 	loggingFilter.setIncludeClientInfo(true); // logging include client info
	// 	loggingFilter.setIncludeHeaders(true); // include header
	// 	loggingFilter.setIncludeQueryString(true); // include querystring
	// 	loggingFilter.setIncludePayload(true);	// include body
	// 	loggingFilter.setMaxPayloadLength(64000); // include body size
	// 	return loggingFilter;
	// }

}
