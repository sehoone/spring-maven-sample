package com.sehoon.springmavensample.common.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sehoon.springmavensample.common.security.service.UserDetailsServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * Filters incoming requests and installs a Spring Security principal if a header corresponding to a valid user is
 * found.
 */
@Slf4j
@Order(0)
public class JWTFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	// private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			log.info("doFilterInternal start");
			String jwt = parseJwt(request);
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
				String username = jwtUtils.getUserNameFromJwtToken(jwt);

				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				// UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetails;

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}

		filterChain.doFilter(request, response);
	}

	private String parseJwt(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
	}
    // public static final String AUTHORIZATION_HEADER = "Authorization";

    // private final TokenProvider tokenProvider;

    // public JWTFilter(TokenProvider tokenProvider) {
    //     this.tokenProvider = tokenProvider;
    // }

    // @Override
    // public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
    //     throws IOException, ServletException {
    //     HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    //     String jwt = resolveToken(httpServletRequest);
    //     if (StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)) {
    //         Authentication authentication = this.tokenProvider.getAuthentication(jwt);
    //         SecurityContextHolder.getContext().setAuthentication(authentication);
    //     }
    //     filterChain.doFilter(servletRequest, servletResponse);
    // }

    // private String resolveToken(HttpServletRequest request) {
    //     String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
    //     log.debug("bearerToken " + bearerToken);
    //     if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
    //         return bearerToken.substring(7);
    //     }
    //     return null;
    // }
}
