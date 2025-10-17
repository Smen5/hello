package com.Smen5.HelloFeedService.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Smen5.HelloFeedService.dto.ChildFeedCreateDto;
import com.Smen5.HelloFeedService.entity.ChildFeed;
import com.Smen5.HelloFeedService.entity.Feed;
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
		log.info("feedno:{}",feedNo);
		Feed feed = feedRepository.findById(feedNo)
				.orElseThrow(()-> new RuntimeException("invalid feed no"));
		
		if(!feed.getAuthorUuid().equals(authorUuid))
			throw new RuntimeException("권한이 없는 피드");
		
		ChildFeed childFeed = ChildFeed.builder()
				.parent(feed)
				.text(dto.getText())
				.build();
		
		childFeedRepository.save(childFeed);
	}
}
