package com.ss.training.spring.lms.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration class that sets up beans used in the application
 *
 *
 */
@Configuration
public class SpringConfig {

	@Bean
	public LibraryService authorServiceBean() {
		return new LibraryService();
	}
}
