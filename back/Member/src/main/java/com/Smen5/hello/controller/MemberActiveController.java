package com.Smen5.hello.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Smen5.hello.service.MemberActiveService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberActiveController {
	private final MemberActiveService memberActiveService;
	@PatchMapping("/active/{uuid}")
	public ResponseEntity<Void> activeMember(@PathVariable String uuid){
		memberActiveService.activeMember(uuid);
		return ResponseEntity.ok().build();
	}
	
	@PatchMapping("/inactive/{uuid}")
	public ResponseEntity<Void> inactiveMember(@PathVariable String uuid){
		memberActiveService.inactiveMember(uuid);
		return ResponseEntity.ok().build();
	}
}
