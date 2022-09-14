package com.qa.demo.persistence.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
	public class AppConfig {

	    @Bean
	    public ModelMapper mapper() {
	        return new ModelMapper();
	    }

}
