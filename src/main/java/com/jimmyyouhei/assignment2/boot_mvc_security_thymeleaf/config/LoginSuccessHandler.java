package com.jimmyyouhei.assignment2.boot_mvc_security_thymeleaf.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.jimmyyouhei.assignment2.boot_mvc_security_thymeleaf.entity.UserRole;


@Configuration
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		
		if(roles.contains("ROLE_" + UserRole.ADMIN.name())) {
			response.sendRedirect(request.getContextPath() + "/admin");
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
		
	}

}

