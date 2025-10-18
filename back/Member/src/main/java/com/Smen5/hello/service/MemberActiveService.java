package com.Smen5.hello.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Smen5.hello.constant.RoleConstant;
import com.Smen5.hello.dto.MemberResponseDto;
import com.Smen5.hello.entity.Member;
import com.Smen5.hello.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberActiveService {
	private final MemberRepository memberRepository;
	
	@Transactional
	public void activeMember(String userUuid, String memberUuid) {
		Member member = memberRepository.findByUuid(memberUuid)
				.orElseThrow(()->new RuntimeException("사용자가 존재하지 않습니다."));
		member.active();
	}
	
	@Transactional
	public void inactiveMember(String userUuid, String memberUuid) {
		Member member = memberRepository.findByUuid(memberUuid)
				.orElseThrow(()->new RuntimeException("사용자가 존재하지 않습니다."));
		member.inactive();
	}
	
	@Transactional(readOnly=true)
	public List<MemberResponseDto> getPendingMembers(String userUuid){
		Member user = memberRepository.findByUuid(userUuid)
				.orElseThrow(()->new RuntimeException("잘못된 접근"));
		
		if(user.getRole() != RoleConstant.ROLE_MEMBER)
			throw new RuntimeException("권한 없음");
		
		List<Member> member = memberRepository.findByRole(RoleConstant.ROLE_PENDING);
		return member.stream()
				.map((m)-> MemberResponseDto.builder()
						.uuid(m.getUuid())
						.name(m.getName())
						.avatarUrl(m.getAvatar_url())
						.role(m.getRole())
						.build())
						.toList();
	}
}
