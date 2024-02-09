package com.ingsoftware.munchies;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableScheduling
@ServletComponentScan
@OpenAPIDefinition(info = @Info(title = "Restaurant APIS", version = "1.0", description = "Restaurant management Apis"))
public class MunchiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MunchiesApplication.class, args);
	}
}
