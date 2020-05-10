package com.ss.training.spring.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.training.spring.lms.entity.BookCopies;
import com.ss.training.spring.lms.entity.BookCopiesKey;
import com.ss.training.spring.lms.entity.LibraryBranch;
import com.ss.training.spring.lms.service.LibraryService;

@RestController
@RequestMapping("/lms/library/")
public class LibraryController {

	@Autowired
	LibraryService libraryService;

	// read all branches
	@GetMapping("/branches")
	public List<LibraryBranch> getAllBranches() {
		return libraryService.readAllLibraryBranch();
	}

	// update all branches
	@PutMapping("/branches/{branchId}")
	public LibraryBranch updateLibraryBranch(@RequestBody LibraryBranch libraryBranch, @PathVariable Integer branchId) {
		libraryBranch.setBranchId(branchId);
		return libraryService.updateLibraryBranch(libraryBranch);
	}

	// read all book copies
	@GetMapping("/branches/{branchId}/bookCopies")
	public List<BookCopies> getAllLibraryBookCopies() {
		return libraryService.readAllBookCopiesByBranch();
	}

	// update number of book copies
	@PutMapping("/branches/{branchId}/bookCopies")
	public BookCopies addBookCopiesToBranch(@RequestBody BookCopies bookCopies, @PathVariable Long branchId) {
		//set BookCopiesKey id to branId from path
		BookCopiesKey id = new BookCopiesKey();
		id.setBranchId(branchId);
		//set BookCopies branchId to branId from BookCopiesKey
		bookCopies.setId(id);
		return libraryService.updateBookCopies(bookCopies);
	}

}
