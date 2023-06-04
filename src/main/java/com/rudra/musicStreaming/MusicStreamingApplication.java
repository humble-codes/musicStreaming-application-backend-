package com.rudra.musicStreaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MusicStreamingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicStreamingApplication.class, args);
	}

}
