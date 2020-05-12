package com.ss.training.spring.lms.service.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ss.training.spring.lms.service.AdminService;

public class AdminServiceReadTest {
	
	@Autowired
	AdminService adminService;

	@Test
	public void findAllAuthors()	{
		
		
		assertNotNull(adminService.findAllAuthors());
	}

}
