package com.Smen5.hellogateway.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	@Value("${spring.jwt.key}")
	String key;
	 public Claims validateToken(String token) {
		 try {
			 Key secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
	         Jws<Claims> claimsJws = Jwts.parserBuilder()
	                 .setSigningKey(secretKey)
	                 .build()
	                 .parseClaimsJws(token);
	         return claimsJws.getBody();
		 }catch(Exception e) {
			 throw new RuntimeException("Invalid JWT token", e);
		 }
	}
}
