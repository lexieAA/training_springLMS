package com.ss.training.spring.lms;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ss.training.spring.lms.dao.AuthorDAO;
import com.ss.training.spring.lms.service.AdminService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LmsApplicationTests {
	
	@Autowired
	private AuthorDAO aDAO;
	
	@Test
	void contextLoads() {
	}
	
	

	@Test
	public void findAllAuthors()	{
		
		
		assertNotNull(aDAO.findAll());
	}

}
