package com.Smen5.HelloFeedService.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.Smen5.HelloFeedService.entity.Feed;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedResponseDto {
	MemberDto author;
	Long no;
	LocalDateTime createdAt;
	String text;
	List<ChildFeedDto> childFeeds;
	public FeedResponseDto(Feed feed, MemberDto authorDto, List<ChildFeedDto> childFeeds) {
		this.author = authorDto;
		this.no = feed.getId();
		this.createdAt = feed.getCreateDate();
		this.text = feed.getText();
		this.childFeeds=childFeeds;
	}
}
