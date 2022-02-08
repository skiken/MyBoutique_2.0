package com.training.MyBoutique_20.web;

import java.time.LocalTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomeEndpoint {
	
	 @GetMapping("/user")
	    public String user() {
	        return ("<h1>Welcome User</h1>");
	    }
	 @GetMapping("/hello")
		public String hello() {
			return "hello, this is a test , now it's: "+ LocalTime.now();
		}

}
