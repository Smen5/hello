package com.Smen5.hello.component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtProvider {
	@Value("${spring.jwt.key}")
	private String key;
	@Value("${spring.jwt.term}")
	private long term;
	public String generateToken(String uuid, Map<String,Object> claims) {
		Date now = new Date();
		Date expire = new Date(now.getTime() + term);
		SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
		return Jwts.builder()
				.setSubject(uuid)
				.claim("role", (String)claims.get("role"))
				.setIssuedAt(now)
				.setExpiration(expire)
				.signWith(secretKey,SignatureAlgorithm.HS256)
				.compact();
	}
}
