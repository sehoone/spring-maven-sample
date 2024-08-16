package com.sehoon.springmavensample.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import com.sehoon.springmavensample.common.security.jwt.JWTFilter;



@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration {

    private final ApplicationProperties applicationProperties;

    private final CorsFilter corsFilter;
    private final SecurityProblemSupport problemSupport;

    public SecurityConfiguration(
        CorsFilter corsFilter,
        ApplicationProperties applicationProperties,
        SecurityProblemSupport problemSupport
    ) {
        this.corsFilter = corsFilter;
        this.problemSupport = problemSupport;
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web ->
            web
                .ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/app/**/*.{js,html}")
                .antMatchers("/i18n/**")
                .antMatchers("/content/**")
                .antMatchers("/swagger-ui/**")
                .antMatchers("/test/**");
    }

	@Bean
	public JWTFilter authenticationJwtTokenFilter() {
		return new JWTFilter();
	}
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf()
            .disable()
            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)    // CORS 필터 추가
            .exceptionHandling()    // 인증 실패 및 접근 거부 시 problemSupport를 사용하여 예외를 처리합니다.
                .authenticationEntryPoint(problemSupport)
                .accessDeniedHandler(problemSupport)
        .and()
            .headers()  // Content Security Policy(CSP)를 설정합니다. CSP는 웹 애플리케이션이 허용하는 콘텐츠 소스를 정의하는 보안 정책. 이를 통해 XSS(Cross-Site Scripting) 및 데이터 인젝션 공격을 방지
            .contentSecurityPolicy(applicationProperties.getContentSecurityPolicy())
        .and()
            .frameOptions() // 프레임 내에서 페이지가 로드되지 않도록 설정합니다.
            .deny()
        .and()
        // 세션을 상태 비저장 방식으로 설정합니다. 즉, 서버는 세션 상태를 저장하지 않습니다. ALWAYS: 항상세션생성, IF_REQUIRED: 세션이 필요할때 생성, NEVER: 세션을 생성하진않지만 이미존재하면 사용, STATELESS: 세션을 사용하지않음
            .sessionManagement()    
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()    // 특정 경로에 대한 접근 권한을 설정합니다.
            .antMatchers("/api/sample/**").permitAll()
            .antMatchers("/api/user/init").permitAll()
            .antMatchers("/api/**").authenticated()
        .and()
            .httpBasic()
        .and()
        .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class); // JWT 필터 추가
        return http.build();
        // @formatter:on
    }

    // private JWTConfigurer securityConfigurerAdapter() {
    //     return new JWTConfigurer(tokenProvider);
    // }
}
