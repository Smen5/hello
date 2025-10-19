package com.Smen5.HelloFeedService.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Smen5.HelloFeedService.dto.ChildFeedCreateDto;
import com.Smen5.HelloFeedService.dto.FeedCreateDto;
import com.Smen5.HelloFeedService.dto.FeedResponseDto;
import com.Smen5.HelloFeedService.service.ChildFeedService;
import com.Smen5.HelloFeedService.service.FeedService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class FeedController {
	private final FeedService feedService;
	private final ChildFeedService childFeedService;
	/**
	 * create feed api
	 * @param userUuid
	 * @param dto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Void> createFeed(@AuthenticationPrincipal String userUuid, @RequestBody FeedCreateDto dto){
		feedService.createFeed(dto, userUuid);
		return ResponseEntity.ok().build();
	}
	/**
	 * get feeds with pageable api
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<FeedResponseDto>> getFeeds(@RequestParam("page")int page, @RequestParam("size")int size){
		return ResponseEntity.ok(feedService.getFeeds(page, size));
	}
	/**
	 * get single feed with child feed api
	 * @param feedNo
	 * @return
	 */
	@GetMapping("/{feedNo}")
	public ResponseEntity<FeedResponseDto> getFeed(@PathVariable("feedNo") Long feedNo){
		return ResponseEntity.ok(feedService.getFeed(feedNo));
	}
	/**
	 * crate child feed api
	 * @param userUuid
	 * @param feedNo
	 * @param dto
	 * @return
	 */
	@PostMapping("/{feedNo}")
	public ResponseEntity<Void> createChildFeed(@AuthenticationPrincipal String userUuid, @PathVariable("feedNo") Long feedNo, @RequestBody ChildFeedCreateDto dto){
		childFeedService.createChildFeed(userUuid, feedNo, dto);
		return ResponseEntity.ok().build();
	}
	/**
	 * delete feed api
	 * @param userUuid
	 * @param feedNo
	 * @return
	 */
	@DeleteMapping("/{feedNo}")
	public ResponseEntity<Void> deleteFeed(@AuthenticationPrincipal String userUuid, @PathVariable("feedNo") Long feedNo){
		log.info("test{}",userUuid);
		feedService.deleteFeed(userUuid, feedNo);
		return ResponseEntity.ok().build();
	}
	/**
	 * delete child feed api
	 * @param userUuid
	 * @param childFeedNo
	 * @return
	 */
	@DeleteMapping("/child/{childFeedNo}")
	public ResponseEntity<Void> deleteChildFeed(@AuthenticationPrincipal String userUuid, @PathVariable("childFeedNo") Long childFeedNo){
		childFeedService.deleteChildFeed(userUuid, childFeedNo);
		return ResponseEntity.ok().build();
	}
}
