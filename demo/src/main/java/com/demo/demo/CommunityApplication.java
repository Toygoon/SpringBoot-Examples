package com.demo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// "RestController" means (Controller + ResponseBody)
@RestController
@SpringBootApplication
public class CommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

	// GetMapping maps the HTTP protocol of "GET"
	// If the "value" is not set, the default value "" will be set
	@GetMapping
	public String HelloWorld() {
		return "Hello World";
	}
}
