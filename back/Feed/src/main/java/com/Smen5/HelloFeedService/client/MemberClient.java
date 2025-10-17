package com.Smen5.HelloFeedService.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Smen5.HelloFeedService.dto.MemberDto;

@FeignClient(name = "member-service", url = "${app.service.member}") 
public interface MemberClient {
	@PostMapping("/internal/members")
	Map<String,MemberDto> getMemberBulk(@RequestBody List<String> uuids);
}
