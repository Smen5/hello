package com.Smen5.HelloFeedService.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Smen5.HelloFeedService.dto.ChildFeedCreateDto;
import com.Smen5.HelloFeedService.entity.ChildFeed;
import com.Smen5.HelloFeedService.entity.Feed;
import com.Smen5.HelloFeedService.exception.FeedException;
import com.Smen5.HelloFeedService.repository.ChildFeedRepository;
import com.Smen5.HelloFeedService.repository.FeedRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChildFeedService {
	private final FeedRepository feedRepository;
	private final ChildFeedRepository childFeedRepository;
	
	@Transactional
	public void createChildFeed(String authorUuid, Long feedNo, ChildFeedCreateDto dto) {
		Feed feed = feedRepository.findById(feedNo)
				.orElseThrow(()-> new FeedException("잘못된 피드 접근",HttpStatus.BAD_REQUEST));
		
		if(!feed.getAuthorUuid().equals(authorUuid))
			throw new FeedException("권한이 없는 피드",HttpStatus.UNAUTHORIZED);
		
		ChildFeed childFeed = ChildFeed.builder()
				.parent(feed)
				.text(dto.getText())
				.build();
		log.info("피드 삭제 요청: author={}, feedNo={}", authorUuid, feedNo);
		childFeedRepository.save(childFeed);
	}
	
	@Transactional
	public void deleteChildFeed(String authorUuid, Long childFeedNo) {
		ChildFeed childFeed = childFeedRepository.findById(childFeedNo)
				.orElseThrow(()-> new FeedException("잘못된 자식 피드 접근",HttpStatus.BAD_REQUEST));
		Feed parentFeed = childFeed.getParent();
		if(!parentFeed.getAuthorUuid().equals(authorUuid))
			throw new FeedException("피드 권한 없음",HttpStatus.FORBIDDEN);
		log.info("자식 피드 삭제 요청: author={}, childFeedNo={}", authorUuid, childFeedNo);
		childFeedRepository.delete(childFeed);
	}
}
