package com.codefuentes.javacodechallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.codefuentes.javacodechallenge.model")
@EnableJpaRepositories("com.codefuentes.javacodechallenge")
@ComponentScan("com.codefuentes.javacodechallenge")
public class JavacodechallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavacodechallengeApplication.class, args);
	}
}
