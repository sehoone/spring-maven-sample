package com.sehoon.springmavensample.common.security;

// import java.util.Collection;
// import java.util.Optional;
// import java.util.stream.Collectors;

// import org.apache.commons.lang3.StringUtils;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.authentication.LockedException;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;

// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Component
// public class AccountAuthenticationProvider implements AuthenticationProvider {

//     private final DomainUserDetailsService domainUserDetailsService;
//     private final PasswordEncoder passwordEncoder;
//     // private final AdminAccountService adminAccountService;

//     public static final String BAD_CREDENTIALS = "아이디 또는 비밀번호가 잘못 입력 되었습니다.<br/>아이디와 비밀번호를 정확히 입력해 주세요.";

//     public static final String AUTH_BLOCK = "10회 입력 오류로 인하여 계정이 차단 되었습니다.<br/>마스터 관리자에게 문의 하세요.";

//     public AccountAuthenticationProvider(
//         DomainUserDetailsService domainUserDetailsService,
//         PasswordEncoder passwordEncoder,
//         AdminAccountService adminAccountService
//     ) {
//         this.domainUserDetailsService = domainUserDetailsService;
//         this.passwordEncoder = passwordEncoder;
//         this.adminAccountService = adminAccountService;
//     }

//     @Override
//     public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//         log.debug("authenticate start");
//         final String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
//         if (StringUtils.isEmpty(username)) {
//             throw new BadCredentialsException(BAD_CREDENTIALS);
//         }
//         // get user details using Spring security user details service
//         UserDetails user = null;
//         try {
//             user = domainUserDetailsService.loadUserByUsername(username);
//             log.debug("loadUser " + user.toString());
//         } catch (UsernameNotFoundException exception) {
//             //없는 사용자인 경우
//             throw new UsernameNotFoundException(BAD_CREDENTIALS);
//         }
//         //패스워드 없는 경우
//         if (authentication.getCredentials() == null) {
//             log.debug("Failed to authenticate no credentials");
//             throw new BadCredentialsException(BAD_CREDENTIALS);
//         }
//         //계정 차단여부 확인
//         Optional<AdminAccountDTO> adminAccount = adminAccountService.findOneByAdminAccount(username);
//         adminAccount.ifPresent(v -> {
//             if(v.getAuthStatusCode().equals(AdminAccountStatus.BLOCK)) {
//                 throw  new LockedException(AUTH_BLOCK);
//             }
//         });

//         //패스워드 확인
//         String presentedPassword = authentication.getCredentials().toString();
//         //패스워드 불일치시
//         if (!this.passwordEncoder.matches(presentedPassword, user.getPassword())) {
//             log.debug("Failed to authenticate password does not match stored value");
//             String passwordErrorText = BAD_CREDENTIALS;
//             //패스워드에러 카운트
//             if (adminAccount.isPresent()) {
//                 int errCnt = adminAccount.get().getPwdErrCnt();
//                 if (errCnt < 10) {
//                     //에러카운터 증가
//                     Optional<AdminAccountDTO> cntResult = adminAccountService.errorCount(username);
//                     if (cntResult.isPresent()) {
//                         errCnt = cntResult.get().getPwdErrCnt();
//                         if (errCnt >= 5 && errCnt < 10) {
//                             passwordErrorText = "현재 " + errCnt + "회 틀렸습니다.<br/>입력 오류 10회 시 계정이 차단됩니다.";
//                         } else if (errCnt >= 10) {
//                             //10회시 잠금 처리
//                             throw new LockedException(AUTH_BLOCK);
//                         }
//                     }
//                 }
//             }
//             throw new BadCredentialsException(passwordErrorText);
//         }

//         return createAuthentication(authentication, user);
//     }

//     /**
//      * Creates a new authentication with basic roles
//      * @param auth Contains auth details that will be copied into the new one.
//      * @param user User object representing who is logging in
//      * @return Authentication
//      */
//     private Authentication createAuthentication(Authentication auth, UserDetails user) {
//         Collection<? extends GrantedAuthority> authorities = user
//             .getAuthorities()
//             .stream()
//             .map(a -> new SimpleGrantedAuthority(a.getAuthority()))
//             .collect(Collectors.toSet());

//         return new UsernamePasswordAuthenticationToken(
//             user.getUsername(),
//             auth.getCredentials().toString(),
//             authorities
//         );
//     }

//     @Override
//     public boolean supports(Class<?> authentication) {
//         return (authentication.equals(UsernamePasswordAuthenticationToken.class));
//     }
// }
