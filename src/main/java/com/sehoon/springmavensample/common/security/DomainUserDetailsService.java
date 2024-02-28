package com.sehoon.springmavensample.common.security;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Locale;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Component;
// import org.springframework.transaction.annotation.Transactional;

// import lombok.extern.slf4j.Slf4j;
// import com.sehoon.springmavensample.common.module.adminauth.domain.AdminAccount;
// import com.sehoon.springmavensample.common.module.adminauth.repository.AdminAccountRepository;

// /**
//  * Authenticate a user from the database.
//  * spring security 의 UserDetailsService 구현부. 관리자 계정 로그인에 사용
//  */
// @Slf4j
// @Component("userDetailsService")
// public class DomainUserDetailsService implements UserDetailsService {

//     private final AdminAccountRepository adminAccountRepository;

//     public DomainUserDetailsService(AdminAccountRepository adminAccountRepository) {
//         this.adminAccountRepository = adminAccountRepository;
//     }

//     @Override
//     @Transactional
//     public UserDetails loadUserByUsername(final String login) {
//         log.debug("Authenticating {}", login);

//         String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);

//         return adminAccountRepository
//             .findOneByAdminAccount(lowercaseLogin)
//             .map(adminAccount -> createSpringSecurityUser(lowercaseLogin, adminAccount))
//             .orElseThrow(() -> new UsernameNotFoundException("adminAccount " + lowercaseLogin + " was not found in the database"));
//     }

//     private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, AdminAccount adminAccount) {
//         // admin권한 관리를 안하여 하드코딩. 권한 제어가 필요하면 GrantedAuthority 수정필요
//         List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
//         grantedAuthorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.ADMIN));

//         return new org.springframework.security.core.userdetails.User(
//             adminAccount.getAdminAccount(),
//             adminAccount.getAdminPwd(),
//             grantedAuthorities
//         );
//     }
//     // private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
//     //     if (!user.isActivated()) {
//     //         throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
//     //     }
//     //     List<GrantedAuthority> grantedAuthorities = user
//     //         .getAuthorities()
//     //         .stream()
//     //         .map(authority -> new SimpleGrantedAuthority(authority.getName()))
//     //         .collect(Collectors.toList());
//     //     return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
//     // }
// }
