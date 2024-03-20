package com.ssafy.triptogether;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TripTogetherApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripTogetherApplication.class, args);
	}

}
