package com.mutantes.mutantesdbapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MutantesDbApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutantesDbApiApplication.class, args);
	}

}
