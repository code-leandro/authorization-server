package com.leandrosouza.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.leandrosouza.user.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableResourceServer
@RequiredArgsConstructor
public class AuthenticationManagerConfiguration extends GlobalAuthenticationConfigurerAdapter {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
		.passwordEncoder(passwordEncoder);
	}
}
