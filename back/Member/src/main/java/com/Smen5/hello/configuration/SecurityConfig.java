package com.Smen5.hello.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.Smen5.hello.configuration.oauth.OAtuh2SuccessHandler;
import com.Smen5.hello.service.GitHubOAuth2UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final GitHubOAuth2UserService gitHubOAuth2UserService;
	private final OAtuh2SuccessHandler oAtuh2SuccessHandler;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
			.csrf(CsrfConfigurer::disable)
			.httpBasic(HttpBasicConfigurer::disable)
			.formLogin(FormLoginConfigurer::disable)
	        .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(auth-> auth.requestMatchers("/api/login/**").permitAll().anyRequest().authenticated())
	        .oauth2Login(oauth2-> oauth2
	        	.userInfoEndpoint(userInfo -> userInfo.userService(gitHubOAuth2UserService))
	        	.successHandler(oAtuh2SuccessHandler));
        return http.build();
	}
}