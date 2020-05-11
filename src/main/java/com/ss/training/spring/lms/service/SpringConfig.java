package com.ss.training.spring.lms.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration class that sets up beans used in the application
 * 
 * @author jalveste
 *
 */
@Configuration
public class SpringConfig {
//	public ConnectionUtil connUtil = new ConnectionUtil();

	@Bean
	public AdminService authorServiceBean() {
		return new AdminService();
	}
}
