package com.qa.demo.persistence.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.modelmapper.ModelMapper;

@Configuration
@Profile({"dev", "prod"})
	public class AppConfig {

	    @Bean
	    public ModelMapper mapper() {
	        return new ModelMapper();
	    }

}
