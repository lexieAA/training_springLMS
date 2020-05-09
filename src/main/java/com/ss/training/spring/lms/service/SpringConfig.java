package com.ss.training.spring.lms.service;

import java.sql.Connection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ss.training.spring.lms.dao.AuthorDAO;
import com.ss.training.spring.lms.dao.BookCopiesDAO;
import com.ss.training.spring.lms.dao.BookDAO;
import com.ss.training.spring.lms.dao.BookLoanDAO;
import com.ss.training.spring.lms.dao.BorrowerDAO;
import com.ss.training.spring.lms.dao.GenreDAO;
import com.ss.training.spring.lms.dao.LibraryBranchDAO;
import com.ss.training.spring.lms.dao.PublisherDAO;

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

	@Bean
	public BookCopiesDAO bookCopiesDAOBean() {
		Connection conn = null;
		conn = connUtil.getConnection();
		return new BookCopiesDAO(conn);
	}

	@Bean
	public BookDAO bookDAOBean() {
		Connection conn = null;
		conn = connUtil.getConnection();
		return new BookDAO(conn);
	}

	@Bean
	public BookLoanDAO bookLoanDAOBean() {
		Connection conn = null;
		conn = connUtil.getConnection();
		return new BookLoanDAO(conn);
	}

	@Bean
	public BorrowerDAO borrowerDAOBean() {
		Connection conn = null;
		conn = connUtil.getConnection();
		return new BorrowerDAO(conn);
	}

	@Bean
	public GenreDAO genreDAOBean() {
		Connection conn = null;
		conn = connUtil.getConnection();
		return new GenreDAO(conn);
	}

	@Bean
	public LibraryBranchDAO libraryBranchDAOBean() {
		Connection conn = null;
		conn = connUtil.getConnection();
		return new LibraryBranchDAO(conn);
	}

	@Bean
	public PublisherDAO publisherDAOBean() {
		Connection conn = null;
		conn = connUtil.getConnection();
		return new PublisherDAO(conn);
	}
}
