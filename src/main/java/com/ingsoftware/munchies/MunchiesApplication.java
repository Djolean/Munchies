package com.ingsoftware.munchies;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Restaurant APIS", version = "1.0", description = "Restaurant Managment Apis"))
public class MunchiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MunchiesApplication.class, args);
	}
}
