package com.ss.training.spring.lms.service;

import java.sql.Connection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ss.training.spring.lms.dao.AuthorDAO;

/**
 * Spring configuration class that sets up beans used in the application
 * 
 * @author jalveste
 *
 */
@Configuration
public class SpringConfig {
	public ConnectionUtil connUtil = new ConnectionUtil();

	@Bean
	public AdminService authorServiceBean() {
		return new AdminService();
	}

	@Bean
	public AuthorDAO authorDAOBean() {
		Connection conn = null;
		conn = connUtil.getConnection();
		return new AuthorDAO(conn);
	}
}
