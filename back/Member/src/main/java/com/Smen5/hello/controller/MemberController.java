package com.Smen5.hello.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Smen5.hello.dto.MemberResponseDto;
import com.Smen5.hello.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
	private final MemberService memberService;
	
	@GetMapping("/me")
	public ResponseEntity<MemberResponseDto> getMe(@AuthenticationPrincipal String userUuid) {
		return ResponseEntity.ok(memberService.getMember(userUuid));
	}
	
	@GetMapping("/{uuid}")
	public ResponseEntity<MemberResponseDto> getMember(@PathVariable String uuid){
		return ResponseEntity.ok(memberService.getMember(uuid));
	}
}