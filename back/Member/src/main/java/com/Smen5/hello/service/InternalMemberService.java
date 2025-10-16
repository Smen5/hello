package com.Smen5.hello.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Smen5.hello.dto.MemberResponseDto;
import com.Smen5.hello.entity.Member;
import com.Smen5.hello.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class InternalMemberService {
	private final MemberRepository memberRepository;
	
	@Transactional(readOnly=true)
	public Map<String,MemberResponseDto> getMembers(List<String>uuids){
		List<Member> members = memberRepository.findAllByUuid(uuids);
		return members.parallelStream()
	            .collect(Collectors.toMap(
	                // Member 엔티티의 UUID를 추출
	                Member::getUuid,
	                // Member 엔티티를 DTO로 변환
	                member -> MemberResponseDto.builder()
	                        .uuid(member.getUuid())
	                        .avatarUrl(member.getAvatar_url())
	                        .name(member.getName())
	                        .role(member.getRole())
	                        .build()));
	}
}
