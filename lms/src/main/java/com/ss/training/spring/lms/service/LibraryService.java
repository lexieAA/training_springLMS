package com.ss.training.spring.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ss.training.spring.lms.dao.BookCopiesDAO;
import com.ss.training.spring.lms.dao.LibraryBranchDAO;
import com.ss.training.spring.lms.entity.Book;
import com.ss.training.spring.lms.entity.BookCopies;
import com.ss.training.spring.lms.entity.BookCopiesKey;
import com.ss.training.spring.lms.entity.LibraryBranch;

public class LibraryService {

	@Autowired
	BookCopiesDAO bookCopiesDao;

	@Autowired
	LibraryBranchDAO libraryBranchDao;

	public BookCopies updateBookCopies(BookCopies copy) {
		return bookCopiesDao.save(copy);
	}

	public LibraryBranch updateLibraryBranch(LibraryBranch branch) {
		return libraryBranchDao.save(branch);

	}

	public List<LibraryBranch> readAllLibraryBranch() {
		return libraryBranchDao.findAll();
	}

	public List<BookCopies> readBookCopiesByBranchAndBook(Long branchId, Long bookId) {
		
		return bookCopiesDao.findAll();

	}

}
