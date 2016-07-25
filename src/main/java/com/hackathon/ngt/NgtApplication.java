package com.hackathon.ngt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class NgtApplication {

	public static void main(String[] args) {
		SpringApplication.run(NgtApplication.class, args);
		System.out.println("hello>>>> ");
	}
}
