package com.api.image;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ImageServeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageServeServiceApplication.class, args);
	}

}
