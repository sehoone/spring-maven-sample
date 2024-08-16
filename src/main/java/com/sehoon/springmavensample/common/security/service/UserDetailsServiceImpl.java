package com.sehoon.springmavensample.common.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sehoon.springmavensample.module.user.repository.UserRepositoryCustom;
import com.sehoon.springmavensample.module.user.service.dto.UserAppDeviceDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepositoryCustom userRepositoryCustom;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		UserAppDeviceDTO userDetailDTO = userRepositoryCustom.findUserAppdeviceByAccount(account)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with userId: " + account));
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USER"));

		return UserDetailsImpl.builder().id(userDetailDTO.getId()).account(userDetailDTO.getAccount()).password(userDetailDTO.getPassword())
										.emailAddr(userDetailDTO.getEmailAddr()).name(userDetailDTO.getName())
										.profileUrl(userDetailDTO.getProfileUrl()).uuid(userDetailDTO.getUuid()).osCode(userDetailDTO.getOsCode())
										.appVersion(userDetailDTO.getAppVersion()).isEnabled(true).authorities(authorities).build();
	}

}
