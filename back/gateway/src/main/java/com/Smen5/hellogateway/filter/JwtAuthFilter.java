package com.Smen5.hellogateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.Smen5.hellogateway.util.JwtUtil;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter implements GlobalFilter{
	private final JwtUtil jwtUtil;
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		
		String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		String path = exchange.getRequest().getURI().getPath();
		
		
		//로그인, 회원가입은 jwt 검사 진행 하지 않음
	    if (path.startsWith("/oauth2/") || path.startsWith("/login/oauth2/code/github") || exchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
	        return chain.filter(exchange);
	    }
	    //사용자 인증 정보 없을경우 헤더에 넣지 않음
	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	    	return chain.filter(exchange);
	    }
	    String token = authHeader.substring(7);
	    
	    try {
	    	Claims claims = jwtUtil.validateToken(token);
	    	exchange = exchange.mutate()
	                .request((r) -> {
	                	r.header("X-User-Id", claims.getSubject());
	                	r.header("X-User-Role", (String)claims.get("role"));})
	                .build();
	    	 log.info("Request : {} : {}",claims.getSubject(), (String)claims.get("role"));
	    }catch(Exception e) {
	    	exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
	        return exchange.getResponse().setComplete();
	    }
		return chain.filter(exchange);
	}
}
