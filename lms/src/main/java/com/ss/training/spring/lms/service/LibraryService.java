package com.ss.training.spring.lms.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.training.spring.lms.dao.BookCopiesDAO;
import com.ss.training.spring.lms.dao.LibraryBranchDAO;
import com.ss.training.spring.lms.entity.BookCopies;
import com.ss.training.spring.lms.entity.LibraryBranch;


@Component
public class LibraryService {
	
	@Autowired
	BookCopiesDAO bookCopiesDao;
	
	@Autowired
	LibraryBranchDAO libraryBranchDao;
	
	public void addBookCopies(BookCopies copy) throws SQLException, ClassNotFoundException {
		bookCopiesDao.addBookCopies(copy);
	}
	
	public void updateLibraryBranch(LibraryBranch branch) throws SQLException, ClassNotFoundException {
		libraryBranchDao.updateLibraryBranch(branch);
	}
	

}
