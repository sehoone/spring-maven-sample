package com.sehoon.springmavensample.common.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.sehoon.springmavensample.common.config.ApplicationProperties;
import com.sehoon.springmavensample.common.security.service.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtUtils {

	private final ApplicationProperties applicationProperties;

	private Key getSigninKey(String jwtSecret) {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateJwtToken(Authentication authentication) {

		// UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		String userAccount = (String) authentication.getPrincipal();
		
		return Jwts.builder().setSubject(userAccount).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + (1000 * applicationProperties.getTokenValidityInSeconds())))
				.signWith(getSigninKey(applicationProperties.getJwtBase64Secret())).compact();
	}

	public String generateJwtToken(String token) {
		String username = getUserNameFromJwtToken(token);
		return Jwts.builder().setSubject((username)).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + (1000 * applicationProperties.getTokenValidityInSeconds())))
				.signWith(getSigninKey(applicationProperties.getJwtBase64Secret())).compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigninKey(applicationProperties.getJwtBase64Secret())).build()
				.parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(getSigninKey(applicationProperties.getJwtBase64Secret())).build()
					.parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}

	public String generateRefreshJwtToken(String token) {
		String username = getUserNameFromJwtToken(token);

		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + (1000 * applicationProperties.getTokenValidityInSeconds())))
				.signWith(getSigninKey(applicationProperties.getJwtBase64Secret()))
				.compact();
	}

	/**
     * 로그인한 사용자 조회
     * 로그인을 안했을경우, return null
     * @param 
     * @return UserDetails
     */
	public Optional<UserDetailsImpl> getCurrentUserDetails() {
		UserDetailsImpl userDetail = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetailsImpl) {
			userDetail = (UserDetailsImpl) principal;
		}
		
		return Optional.ofNullable(userDetail);
	}

	public Optional<String> getCurrentUserAccount() {
		String userAccount = null;
		Optional<UserDetailsImpl> userDetail = this.getCurrentUserDetails();

		if(userDetail.isPresent()) {
			userAccount = userDetail.get().getAccount();
		}
		
		return Optional.ofNullable(userAccount);
	}

}
