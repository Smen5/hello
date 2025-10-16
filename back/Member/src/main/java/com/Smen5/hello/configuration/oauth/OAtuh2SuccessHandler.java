package com.Smen5.hello.configuration.oauth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.Smen5.hello.component.JwtProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAtuh2SuccessHandler implements AuthenticationSuccessHandler {
	private final String redirectUrl = "http://localhost:3000";
	private final JwtProvider jwtProvider;
	public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // 인증 성공 시 redirect
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", authentication.getAuthorities().iterator().next().getAuthority());
		
		StringBuilder redirectUrlBuilder = new StringBuilder(redirectUrl);
		
		redirectUrlBuilder.append("/oauth2/callback?token=");
		
		StringBuilder token = new StringBuilder("Bearer ");
		
		token.append(jwtProvider.generateToken(authentication.getName(), claims));
		
		redirectUrlBuilder.append(token);
        
		response.sendRedirect(redirectUrlBuilder.toString());
    }	
}