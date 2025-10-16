package com.Smen5.hello.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.Smen5.hello.entity.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class GitHubOAuth2UserService  extends DefaultOAuth2UserService {
	private final MemberSignupService membersignupService;
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
		OAuth2User oAuth2User = super.loadUser(userRequest);
		Map<String, Object> attributes = oAuth2User.getAttributes();
		Member member = membersignupService.signIn(attributes);
		Map<String,Object> memberAttributes = new HashMap<>();
		memberAttributes.put("uuid", member.getUuid());
		memberAttributes.put("role", member.getRole().toString());
		memberAttributes.put("name", member.getName());
		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(member.getRole().toString())), memberAttributes, "uuid");
	}
}