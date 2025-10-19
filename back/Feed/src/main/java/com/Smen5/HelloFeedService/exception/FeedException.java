package com.Smen5.HelloFeedService.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class FeedException extends RuntimeException{
	private static final long serialVersionUID = 3009224946531007614L;
	@Getter
	private final HttpStatus status;
	public FeedException(String msg, HttpStatus status) {
		super(msg);
		this.status= status;
	}
}