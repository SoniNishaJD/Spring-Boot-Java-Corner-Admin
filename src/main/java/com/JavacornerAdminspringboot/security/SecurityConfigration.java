package com.JavacornerAdminspringboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.JavacornerAdminspringboot.filter.JWTAuthorizationFilter;
import com.JavacornerAdminspringboot.filter.JWTAuthenticationFilter;

import com.JavacornerAdminspringboot.helper.JWTHelper;

@Configuration
@EnableWebSecurity
public class SecurityConfigration {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTHelper jwtHelper;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		 http.cors().and().csrf().disable();
	        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	        http.authorizeRequests().antMatchers("/refresh-token/**").permitAll();
	        http.authorizeRequests().anyRequest().authenticated();
	        http.addFilter(new JWTAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtHelper));
	        http.addFilterBefore(new JWTAuthorizationFilter(jwtHelper), UsernamePasswordAuthenticationFilter.class);
	        return http.build();
	    }
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
	return authConfig.getAuthenticationManager();
	}
}
