package com.Smen5.HelloFeedService.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Smen5.HelloFeedService.client.MemberClient;
import com.Smen5.HelloFeedService.dto.ChildFeedDto;
import com.Smen5.HelloFeedService.dto.FeedCreateDto;
import com.Smen5.HelloFeedService.dto.FeedResponseDto;
import com.Smen5.HelloFeedService.dto.MemberDto;
import com.Smen5.HelloFeedService.entity.ChildFeed;
import com.Smen5.HelloFeedService.entity.Feed;
import com.Smen5.HelloFeedService.repository.ChildFeedRepository;
import com.Smen5.HelloFeedService.repository.FeedRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {
	private final FeedRepository feedRepository;
	private final ChildFeedRepository childFeedRepository;
	private final MemberClient memberClient;
	@Transactional
	public void createFeed(FeedCreateDto dto, String uuid) {
		Feed feed = Feed.builder()
				.authorUuid(uuid)
				.text(dto.getText())
				.build();
		
		
		feedRepository.save(feed);
	}
	
	@Transactional
	public void deleteFeed(String uuid , Long parentFeedId) {
		Feed feed = feedRepository.findById(parentFeedId)
				.orElseThrow(()->new RuntimeException("게시글이 존재하지 않습니다."));
		
		if(!feed.getAuthorUuid().equals(uuid))
			throw new RuntimeException("권한이 없습니다.");
		
		List<ChildFeed> childFeeds = childFeedRepository.findAllByParent(feed);

		childFeedRepository.deleteAll(childFeeds);
		feedRepository.delete(feed);
	}
	
	@Transactional(readOnly=true)
	public FeedResponseDto getFeed(Long feedId) {
		Feed feed = feedRepository.findById(feedId)
				.orElseThrow(()-> new RuntimeException("피드가 존재하지 않습니다."));
		Map<String,MemberDto> memberMap = memberClient.getMemberBulk(Collections.singletonList(feed.getAuthorUuid()));
		List<ChildFeedDto> childFeedDtos = childFeedRepository.findAllByParent(feed).stream().map((c)-> ChildFeedDto.builder()
																								.no(c.getId())
																								.createdAt(c.getCreateDate())
																								.text(c.getText())
																								.build())
																							.toList();
		return new FeedResponseDto(feed, memberMap.get(feed.getAuthorUuid()),childFeedDtos);
	}
	@Transactional(readOnly=true)
	public List<FeedResponseDto> getFeeds(int page, int size){
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createDate"));
		Page<Feed> feeds = feedRepository.findAll(pageable);
		Set<String> authorUuids = new HashSet<>();
		feeds.stream().forEach((f)->authorUuids.add(f.getAuthorUuid()));
		Map<String,MemberDto> memberMap = memberClient.getMemberBulk(authorUuids.stream().toList());
		return feeds.stream().map((f)->{
			MemberDto authorDto = memberMap.get(f.getAuthorUuid());
			return new FeedResponseDto(f,authorDto,null);
		}).toList();
	}
}
