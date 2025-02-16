package com.jobportal.jobportal_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class JobportalDemoApplication extends SpringBootServletInitializer{

	public static void main(String[] args){
		SpringApplication.run(JobportalDemoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(JobportalDemoApplication.class);
	}
}
