package com.ss.training.spring.lms.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ss.training.spring.lms.entity.BookCopies;
import com.ss.training.spring.lms.entity.LibraryBranch;
import com.ss.training.spring.lms.service.LibraryService;

/**
 * 
 *
 */
@RestController
public class LibraryController {
	
	@Autowired
	LibraryService libraryService;
	
	
	@RequestMapping(path = "/lms/library/branches/{branchId}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateLibraryBranch(@RequestBody LibraryBranch libraryBranch) {
		try {
			libraryService.updateLibraryBranch(libraryBranch);
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			return new ResponseEntity<String>("not found", HttpStatus.BAD_REQUEST);
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			return new ResponseEntity<String>("Sql error", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/lms/library/branches")
	public ResponseEntity<List<LibraryBranch>> getAllBranches() {

		// read all branches
		List<LibraryBranch> branches = libraryService.readAllLibraryBranch();

		// a successful request should produce a list not null with a size greater than
		// zero
		if (branches != null && branches.size() > 0) {
			return new ResponseEntity<List<LibraryBranch>>(branches, HttpStatus.OK);
		} else {
			// branches id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(path = "/lms/library/branches/{branchId}/bookCopies")
	public ResponseEntity<List<BookCopies>> getAllLibraryBookCopies(@PathVariable Integer branchId) {

		// read all copies
		List<BookCopies> copies = libraryService.readAllBookCopiesByBranch(branchId);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (copies != null && copies.size() > 0) {
			return new ResponseEntity<List<BookCopies>>(copies, HttpStatus.OK);
		} else {
			// branches id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(path = "/lms/library/branches/{branchId}/bookCopies", method = RequestMethod.PUT)
	public ResponseEntity<String> addBookCopiesToBranch(@RequestBody BookCopies bookCopies, @PathVariable Integer branchId) {
		try {
			bookCopies.setBranchId(branchId);
			libraryService.updateBookCopies(bookCopies);
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			return new ResponseEntity<String>("not found", HttpStatus.BAD_REQUEST);
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			return new ResponseEntity<String>("Sql error", HttpStatus.BAD_REQUEST);
		}
	}

}
