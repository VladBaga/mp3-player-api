package org.fasttrackit.mp3playerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(value = "org.fasttrackit.mp3playerapi")
public class Mp3PlayerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Mp3PlayerApiApplication.class, args);
	}

}
