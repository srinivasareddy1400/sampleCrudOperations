package com.zaravya.sampleCrudOperations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@SpringBootApplication
public class SampleCrudOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleCrudOperationsApplication.class, args);
	}
	
}

