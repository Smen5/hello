package com.Smen5.HelloFeedService.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ChildFeedDto {
	Long no;
	LocalDateTime createdAt;
	String text;
}
