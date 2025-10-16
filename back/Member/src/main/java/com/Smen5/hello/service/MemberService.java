package com.Smen5.hello.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Smen5.hello.dto.MemberResponseDto;
import com.Smen5.hello.entity.Member;
import com.Smen5.hello.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
	private final MemberRepository memberRepository;
	
	@Transactional(readOnly=true)
	public MemberResponseDto getMember(String uuid) {
		Member member = memberRepository.findByUuid(uuid)
				.orElseThrow(()-> new RuntimeException());
		return MemberResponseDto.builder()
				.uuid(member.getUuid())
				.name(member.getName())
				.avatarUrl(member.getAvatar_url())
				.role(member.getRole())
				.build();
	}
}
