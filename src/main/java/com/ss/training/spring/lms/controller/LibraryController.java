package com.ss.training.spring.lms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/lms/public/library/")

public class LibraryController {

	@Autowired
	LibraryService libraryService;

	// ----------------------Branches Mapping --------------------------------
	// read all branches
	@GetMapping("/branches")
	public List<LibraryBranch> getAllBranches() {
		return libraryService.readAllLibraryBranch();
	}

	// read branch by Id
	@GetMapping("/branches/{branchId}")
	public List<LibraryBranch> getBranch(@PathVariable Long branchId) {
		return libraryService.readLibraryBranch(branchId);
	}

	// update all branches
	@PutMapping("/branches/{branchId}")
	public LibraryBranch updateLibraryBranch(@RequestBody LibraryBranch libraryBranch, @PathVariable Long branchId) {
		libraryBranch.setBranchId(branchId);
		return libraryService.updateLibraryBranch(libraryBranch);
	}

	// ----------------------Book Copies Mapping --------------------------------

	// read all book copies by branchId
	@GetMapping("/branches/{branchId}/bookCopies")
	public List<BookCopies> getLibraryBookCopiesByBranch(@PathVariable Long branchId) {
		return libraryService.readBookCopiesByBranchId(branchId);
	}

	// read all book copies by bookCopiesKey
	@GetMapping("/branches/{branchId}/bookCopies/{bookId}")
	public Optional<BookCopies> getAllLibraryBookCopiesByBranchAndBook(@PathVariable Long branchId,
			@PathVariable Long bookId) {
		BookCopiesKey id = new BookCopiesKey(branchId, bookId);
		return libraryService.readBookCopiesByKey(id);
	}

	// update number of book copies
	@PutMapping("/branches/{branchId}/bookCopies/{bookId}")
	public BookCopies addBookCopiesToBranch(@RequestBody BookCopies bookCopies, @PathVariable Long branchId,
			@PathVariable Long bookId) {
		// set BookCopiesKey id to branchId and bookId from path
		BookCopiesKey id = new BookCopiesKey(branchId, bookId);
		// set BookCopies BookCopiesKey
		bookCopies.setId(id);
		return libraryService.updateBookCopies(bookCopies);
	}
	
	// delete all book copies by branchId
		@DeleteMapping("/branches/{branchId}/bookCopies/delete")
		public void deleteByIdBranch(@PathVariable Long branchId) {
			//TO DO create a list of all bookcopies by branch id use function in dao
			//TO DO a loop to go through the list a delete
			Long bookId = (long) 1;
			// set BookCopiesKey id to branchId and bookId from path
			BookCopiesKey id = new BookCopiesKey(branchId, bookId);
			libraryService.delateBookCopies(id);
		}

}
