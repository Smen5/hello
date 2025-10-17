package com.Smen5.HelloFeedService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableFeignClients(basePackages = "com.Smen5.HelloFeedService.client")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class HelloFeedServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloFeedServiceApplication.class, args);
	}
}
