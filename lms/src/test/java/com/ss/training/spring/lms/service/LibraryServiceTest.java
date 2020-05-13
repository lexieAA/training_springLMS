package com.ss.training.spring.lms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ss.training.spring.lms.dao.BookCopiesDAO;
import com.ss.training.spring.lms.dao.LibraryBranchDAO;
import com.ss.training.spring.lms.entity.BookCopies;
import com.ss.training.spring.lms.entity.BookCopiesKey;
import com.ss.training.spring.lms.entity.LibraryBranch;

@SpringBootTest
public class LibraryServiceTest {
	@Autowired
	BookCopiesDAO bookCopiesDao;

	@Autowired
	LibraryBranchDAO libraryBranchDao;

	// -----------------Library Branch Services Tests---------------------
	@Test
	public void updateLibraryBranchNull() {
		LibraryBranch noBranchEntered = new LibraryBranch();
		assertEquals(libraryBranchDao.save(noBranchEntered), noBranchEntered);
	}

	@Test
	public void readAllLibraryBranchNotNull() {
		assertNotNull(libraryBranchDao.findAll());
	}

	@Test
	public void readLibraryBranchNotBiggerThanOne() {
		Long branchId = (long) 1;
		assertEquals(libraryBranchDao.findByBranchId(branchId).size(), 1);
	}

	@Test
	public void readLibraryBranchNull() {
		Long branchId = null;
		assertTrue(libraryBranchDao.findByBranchId(branchId).isEmpty());
	}

	// -----------------Book Copies Services Test---------------------

	@Test
	public void readBookCopiesByKeyNotNull() {
		Long bId = (long) 1;
		BookCopiesKey id = new BookCopiesKey(bId, bId);
		assertNotNull(bookCopiesDao.findById(id));

	}

	@Test
	public void readBookCopiesByBranchIdNotNull() {
		Long bId = (long) 1;
		assertNotNull(bookCopiesDao.findByIdBranchId(bId));

	}

	@Test
	public void readBookCopiesByBranchIdIsRightCopy() {
		Long bId = (long) 1;
		BookCopiesKey id = new BookCopiesKey(bId, bId);
		BookCopies bookCopy = new BookCopies();
		bookCopy.setId(id);
		assertTrue(bookCopiesDao.findByIdBranchId(bId).contains(bookCopy));

	}

}
