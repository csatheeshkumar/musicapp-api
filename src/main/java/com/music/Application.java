package com.music;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration // Spring Boot Auto Configuration
@ComponentScan(basePackages = "com.music")
@EnableJpaRepositories("com.music.repository")
@EnableSwagger2 // auto generation of API docs
@PropertySource({"classpath:application.properties","classpath:message.properties"})
public class Application extends SpringBootServletInitializer{
	
	public static final Class<Application> applicationClass = Application.class;
	public static final Logger log = LoggerFactory.getLogger(applicationClass);
	
	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure (SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	} 
}
