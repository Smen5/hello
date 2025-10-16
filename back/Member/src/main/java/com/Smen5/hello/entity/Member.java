package com.Smen5.hello.entity;

import com.Smen5.hello.constant.RoleConstant;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="member")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Getter
	private String uuid;
	@Getter
	private String name;
	@Getter
	private String avatar_url;
	@Getter
	@Enumerated(EnumType.STRING)
	private RoleConstant role;
	
	public void active() {
		this.role = RoleConstant.ROLE_MEMBER;
	}
	
	public void inactive() {
		this.role = RoleConstant.ROLE_PENDING;
	}
}