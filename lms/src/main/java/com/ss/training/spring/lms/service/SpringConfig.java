package com.ss.training.spring.lms.service;

import java.sql.Connection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ss.training.spring.lms.dao.BookCopiesDAO;
import com.ss.training.spring.lms.dao.LibraryBranchDAO;

/**
 * Spring configuration class that sets up beans used in the application
 *
 *
 */
@Configuration
public class SpringConfig {
	public ConnectionUtil connUtil = new ConnectionUtil();

	@Bean
	public LibraryService authorServiceBean() {
		return new LibraryService();
	}

	@Bean
	public BookCopiesDAO bookCopiesDAOBean() {
		Connection conn = null;
		conn = connUtil.getConnection();
		return new BookCopiesDAO(conn);
	}
	
	@Bean
	public LibraryBranchDAO librayBranchDAOBean() {
		Connection conn = null;
		conn = connUtil.getConnection();
		return new LibraryBranchDAO(conn);
	}
}
