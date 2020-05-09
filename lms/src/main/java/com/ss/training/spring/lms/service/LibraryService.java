package com.ss.training.spring.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.training.spring.lms.dao.BookCopiesDAO;
import com.ss.training.spring.lms.dao.LibraryBranchDAO;
import com.ss.training.spring.lms.entity.BookCopies;
import com.ss.training.spring.lms.entity.LibraryBranch;

public class LibraryService {

	@Autowired
	BookCopiesDAO bookCopiesDao;

	@Autowired
	LibraryBranchDAO libraryBranchDao;

	public void updateBookCopies(BookCopies copy) throws ClassNotFoundException, SQLException {
		bookCopiesDao.updateBookCopies(copy);
	}

	public void updateLibraryBranch(LibraryBranch branch) throws ClassNotFoundException, SQLException {

		libraryBranchDao.updateLibraryBranch(branch);

	}

	public List<LibraryBranch> readAllLibraryBranch() {

		try {
			return libraryBranchDao.readAllLibraryBranches();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	public List<BookCopies> readAllBookCopiesByBranch(Integer branchId) {
		try {
			return bookCopiesDao.readAllBookCopiesByBranch(branchId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
