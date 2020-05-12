package com.ss.training.spring.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ss.training.spring.lms.dao.BookCopiesDAO;
import com.ss.training.spring.lms.dao.LibraryBranchDAO;
//import com.ss.training.spring.lms.entity.Book;
import com.ss.training.spring.lms.entity.BookCopies;
import com.ss.training.spring.lms.entity.BookCopiesKey;
//import com.ss.training.spring.lms.entity.BookCopiesKey;
import com.ss.training.spring.lms.entity.LibraryBranch;

public class LibraryService {

	@Autowired
	BookCopiesDAO bookCopiesDao;

	@Autowired
	LibraryBranchDAO libraryBranchDao;

	// -----------------Library Branch Services---------------------
	public LibraryBranch updateLibraryBranch(LibraryBranch branch) {
		return libraryBranchDao.save(branch);

	}

	public List<LibraryBranch> readAllLibraryBranch() {
		return libraryBranchDao.findAll();
	}

	public List<LibraryBranch> readLibraryBranch(Long branchId) {
		return libraryBranchDao.findByBranchId(branchId);
	}

	// -----------------Book Copies Services---------------------

	public BookCopies updateBookCopies(BookCopies copy) {
		return bookCopiesDao.save(copy);
	}
	
	public void delateBookCopies(BookCopiesKey id) {
		bookCopiesDao.deleteById(id);
	}

	public Optional<BookCopies> readBookCopiesByKey(BookCopiesKey id) {
		return bookCopiesDao.findById(id);

	}

	public List<BookCopies> readBookCopiesByBranchId(Long branchId) {
		return bookCopiesDao.findByIdBranchId(branchId);

	}

}
