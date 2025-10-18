package com.Smen5.hello.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Smen5.hello.dto.MemberResponseDto;
import com.Smen5.hello.service.MemberActiveService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberActiveController {
	private final MemberActiveService memberActiveService;
	@GetMapping("/active/{uuid}")
	public ResponseEntity<Void> activeMember(@AuthenticationPrincipal String userUuid, @PathVariable("uuid") String uuid){
		memberActiveService.activeMember(userUuid, uuid);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/inactive/{uuid}")
	public ResponseEntity<Void> inactiveMember(@AuthenticationPrincipal String userUuid, @PathVariable("uuid") String uuid){
		memberActiveService.inactiveMember(userUuid, uuid);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/pending")
	public ResponseEntity<List<MemberResponseDto>> getPedingMember(@AuthenticationPrincipal String userUuid){
		return ResponseEntity.ok(memberActiveService.getPendingMembers(userUuid));
	}
}
