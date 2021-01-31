package com.entrevista.bicevida;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class BicevidaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BicevidaApplication.class, args);
	}

}
