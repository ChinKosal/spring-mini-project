package com.example.minispring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.example.minispring.controller.SendMailController;
import com.example.minispring.service.EmailSenderService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;

@OpenAPIDefinition(info = @Info(title = "My API",
       version = "v1",
       description = "This is description"))
@SecurityScheme(
       name = "bearerAuth",
       type = SecuritySchemeType.HTTP,
       scheme = "bearer",
       in = SecuritySchemeIn.HEADER
)

@SpringBootApplication
public class MinispringApplication {
	public static void main(String[] args) {
		SpringApplication.run(MinispringApplication.class, args);
	}
}
