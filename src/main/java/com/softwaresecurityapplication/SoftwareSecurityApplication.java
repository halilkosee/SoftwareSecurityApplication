package com.softwaresecurityapplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ "com.softwaresecurityapplication.*" })
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SOFTWARE_SECURITY_APP API", version = "1.0", description = "Software Security Testing"))
@SecurityScheme(name = "SSTapi", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class SoftwareSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoftwareSecurityApplication.class, args);
    }

}
