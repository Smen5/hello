package com.Smen5.hello.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class MemberException extends RuntimeException{
	private static final long serialVersionUID = -6394399502670159758L;
	@Getter
	private final HttpStatus status;
	public MemberException(String msg, HttpStatus status) {
		super(msg);
		this.status = status;
	}
}