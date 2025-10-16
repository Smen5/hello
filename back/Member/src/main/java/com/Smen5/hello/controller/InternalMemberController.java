package com.Smen5.hello.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Smen5.hello.dto.MemberResponseDto;
import com.Smen5.hello.service.InternalMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/internal")
public class InternalMemberController {
	private final InternalMemberService internalMemberService;
	@GetMapping("/members")
	public ResponseEntity<Map<String,MemberResponseDto>> getMembers(@RequestBody List<String>uuids){
		return ResponseEntity.ok(internalMemberService.getMembers(uuids));
	}
}