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
	public LibraryBranch updateLibraryBranch(@RequestBody LibraryBranch libraryBranch, @PathVariable Long branchId) {
		libraryBranch.setBranchId(branchId);
		return libraryService.updateLibraryBranch(libraryBranch);
	}

	// read all book copies
	@GetMapping("/branches/{branchId}/book/{bookId}")
	public List<BookCopies> getAllLibraryBookCopies(@PathVariable Long branchId, @PathVariable Long bookId) {
		return libraryService.readBookCopiesByBranchAndBook(branchId, bookId);
	}

	// update number of book copies
	@PutMapping("/branches/{branchId}/book/{bookId}")
	public BookCopies addBookCopiesToBranch(@RequestBody BookCopies bookCopies, @PathVariable Long branchId, @PathVariable Long bookId) {
		//set BookCopiesKey id to branchId and bookId from path
		BookCopiesKey id = new BookCopiesKey(branchId, bookId);
		//set BookCopies BookCopiesKey
		bookCopies.setId(id);
		return libraryService.updateBookCopies(bookCopies);
	}

}
