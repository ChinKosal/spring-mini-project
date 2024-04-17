package com.mybatis.springminiproject;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class SpringMiniProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMiniProjectApplication.class, args);
    }

}
