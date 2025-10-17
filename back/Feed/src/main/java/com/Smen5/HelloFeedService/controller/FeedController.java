package com.Smen5.HelloFeedService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Smen5.HelloFeedService.dto.ChildFeedCreateDto;
import com.Smen5.HelloFeedService.dto.FeedCreateDto;
import com.Smen5.HelloFeedService.dto.FeedResponseDto;
import com.Smen5.HelloFeedService.service.ChildFeedService;
import com.Smen5.HelloFeedService.service.FeedService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class FeedController {
	private final FeedService feedService;
	private final ChildFeedService childFeedService;
	@PostMapping
	public ResponseEntity<Void> createFeed(@AuthenticationPrincipal String userUuid, @RequestBody FeedCreateDto dto){
		feedService.createFeed(dto, userUuid);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{feedNo}")
	public ResponseEntity<FeedResponseDto> getFeed(@PathVariable("feedNo") Long feedNo){
		return ResponseEntity.ok(feedService.getFeed(feedNo));
	}
	
	@PostMapping("/{feedNo}")
	public ResponseEntity<Void> createChildFeed(@AuthenticationPrincipal String userUuid, @PathVariable("feedNo") Long feedNo, @RequestBody ChildFeedCreateDto dto){
		childFeedService.createChildFeed(userUuid, feedNo, dto);
		return ResponseEntity.ok().build();
	}
}
