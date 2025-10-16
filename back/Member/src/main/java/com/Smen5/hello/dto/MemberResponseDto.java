package com.Smen5.hello.dto;

import com.Smen5.hello.constant.RoleConstant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
	private String uuid;
	private String name;
	private String avatarUrl;
	private RoleConstant role;
}
