package com.Smen5.hello.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Smen5.hello.constant.RoleConstant;
import com.Smen5.hello.entity.Member;
import com.Smen5.hello.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberSignupService {
	private final MemberRepository memberRepository;
	
	@Transactional
	public Member signIn(Map<String,Object> attributes) {
		String uuid = attributes.get("id").toString();
		String name = (String)attributes.get("login");
		String avatarUrl = (String)attributes.get("avatar_url");
		
		Member member = memberRepository.findByUuid(uuid)
			    .orElseGet(() -> memberRepository.save(
			        Member.builder()
			            .uuid(uuid)
			            .name(name)
			            .avatar_url(avatarUrl)
			            .role(RoleConstant.ROLE_PENDING)
			            .build()
			    ));
		return member;
	}
}