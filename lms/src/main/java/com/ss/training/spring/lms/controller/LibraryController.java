package com.ss.training.spring.lms.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ss.training.spring.lms.entity.LibraryBranch;
import com.ss.training.spring.lms.service.LibraryService;

@RestController
public class LibraryController {
	
	@Autowired
	LibraryService libraryService;
	
	@PutMapping("/library/branches/{branchId}")
	ResponseEntity<LibraryBranch> updateLibraryBranch(@RequestBody LibraryBranch newLibraryBranch, @PathVariable Long id) {

		LibraryBranch libraryBranch = new LibraryBranch();
		libraryBranch.setBranchId((int)(long)id);
		libraryBranch.setBranchName(newLibraryBranch.getBranchName());
		libraryBranch.setBranchAddress(newLibraryBranch.getBranchAddress());
		try {
			libraryService.updateLibraryBranch(libraryBranch);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ClassNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	  }

}
