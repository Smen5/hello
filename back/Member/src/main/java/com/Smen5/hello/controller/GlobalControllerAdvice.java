package com.Smen5.hello.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Smen5.hello.exception.MemberException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> illegalArgumentHandler(IllegalArgumentException exception){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청입니다.");
	}
	
	@ExceptionHandler(MemberException.class)
	public ResponseEntity<String> businessExceptionHandler(MemberException exception){
		return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> allExceptionHandler(Exception exception){
		log.error("서버 내부 오류 발생:",exception);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
	}
}
