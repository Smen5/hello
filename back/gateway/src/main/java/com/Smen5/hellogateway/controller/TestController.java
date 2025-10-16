package com.Smen5.hellogateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class TestController {
	@GetMapping("/gateway-test")
	public Mono<String> test(){
		return Mono.just("test controller response");
	}
}
