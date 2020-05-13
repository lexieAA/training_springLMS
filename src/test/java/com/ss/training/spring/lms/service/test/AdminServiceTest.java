//package com.ss.training.spring.lms.service.test;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import com.ss.training.spring.lms.dao.AuthorDAO;
//import com.ss.training.spring.lms.entity.Author;
//import com.ss.training.spring.lms.service.AdminService;
//
//import org.junit.Test;
//
//@RunWith(MockitoJUnitRunner.class)
//public class AdminServiceTest {
//
//	@Mock
//	AuthorDAO aDAO;
//	
//	@InjectMocks
//	AdminService adminService;
//	
//	@Test
//	public void testGetAllAuthors() {
//		List<Author> aList = new ArrayList<>();
//		Author author1 = new Author();
//		author1.setAuthorId(1L);
//		author1.setAuthorName("Neil G");
//		
//		Author author2 = new Author();
//		author2.setAuthorId(2L);
//		author2.setAuthorName("Bob B");
//		
//		aList.add(author1);
//		aList.add(author2);
//	
//		when(aDAO.findAll()).thenReturn(aList);
//		assertEquals(2, adminService.findAllAuthors().size());
//	}
//
//	public static void main(String[] args) {
//				author1.setAuthorId(1L);
//	}
//}
