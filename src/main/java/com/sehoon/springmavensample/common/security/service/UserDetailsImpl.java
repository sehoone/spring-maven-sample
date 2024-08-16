package com.sehoon.springmavensample.common.security.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

    private Long id;
	private String account;
	private String password;
	private String emailAddr;
	private String name;
	private String profileUrl;
	private String uuid;
    private String osCode;
    private String appVersion;

	private boolean isEnabled;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String account, String password, String emailAddr, String name, String profileUrl,
			String uuid, String osCode, String appVersion, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.account = account;
		this.password = password;
		this.emailAddr = emailAddr;
		this.name = name;
		this.profileUrl = profileUrl;
		this.authorities = authorities;
		this.isEnabled = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

    @Override
    public String getPassword() {
        return password;
    }
 
    @Override
    public String getUsername() {
        return account;
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

}
