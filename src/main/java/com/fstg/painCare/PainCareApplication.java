package com.fstg.painCare;


import java.util.concurrent.TimeUnit;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = "com.fstg.painCare")
public class PainCareApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(PainCareApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry  registry) {

	    // Register resource handler for images
	    registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static")
	            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
	}
	
}
