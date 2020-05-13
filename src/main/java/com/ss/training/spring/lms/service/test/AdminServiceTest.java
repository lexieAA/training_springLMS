//package com.ss.training.spring.lms.service.test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import com.ss.training.spring.lms.dao.AuthorDAO;
//import com.ss.training.spring.lms.dao.BookLoanDAO;
//import com.ss.training.spring.lms.entity.Author;
//import com.ss.training.spring.lms.entity.BookCopiesKey;
//import com.ss.training.spring.lms.entity.BookLoan;
//import com.ss.training.spring.lms.entity.BookLoansKey;
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
//	@Mock
//	BookLoanDAO blDAO;
//	
//	@InjectMocks
//	AdminService adminService;
//	
//	@Test
//	public void testGetAllAuthorsEmpty() {
//		List<Author> aList = new ArrayList<>();
//		Author author1 = new Author();
//		author1.setAuthorId(1L);
//		author1.setAuthorName("Neil G");
//		
//		Author author2 = new Author();
//		author2.setAuthorId(2L);
//		author2.setAuthorName("Bob B");
//	
//		when(aDAO.findAll()).thenReturn(aList);
//		assertEquals(adminService.findAllAuthors().size(), 0);
//	}
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
//		assertEquals(adminService.findAllAuthors().size(), 2);
//	}
//	
//	@Test
//	public void testExtendBookLoan() {
//		BookLoansKey loanKey = new BookLoansKey();
//		LocalDate date = LocalDate.parse("2020-04-05");
//		loanKey.setBookId(1L);
//		loanKey.setBranchId(12L);
//		loanKey.setCardNo(3L);
//		loanKey.setDateOut(date);
//		
//		Author author2 = new Author();
//		author2.setAuthorId(2L);
//		author2.setAuthorName("Bob B");
//		
//		BookLoan returnLoan = new BookLoan();
//		returnLoan.setDueDate(date);
//		returnLoan.setDateIn(null);
//		Optional<BookLoan> optLoan = Optional.of(returnLoan); 
//		
//		when(blDAO.findById(loanKey)).thenReturn(optLoan);
//		when(blDAO.save(optLoan.get())).thenReturn(null);
//		Integer returnInt = 0;
//		assertEquals(adminService.extendBookLoan(loanKey), returnInt);
//	}
//}
