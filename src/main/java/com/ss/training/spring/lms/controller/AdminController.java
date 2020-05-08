package com.ss.training.spring.lms.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.training.spring.lms.entity.Author;
import com.ss.training.spring.lms.entity.Borrower;
import com.ss.training.spring.lms.entity.Genre;
import com.ss.training.spring.lms.entity.Publisher;
import com.ss.training.spring.lms.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	AdminService adminService;

	//
	//
	// AUTHOR mappings ----------------------------------------------------------
	//
	//
	
	@RequestMapping(path = "/lms/admin/authors")
	public ResponseEntity<List<Author>> getAllAuthors() {

		// read all authors
		List<Author> authors = adminService.readAuthors(null, null);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (authors != null && authors.size() > 0) {
			return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(path = "/lms/admin/authors/id/{authorId}")
	public ResponseEntity<List<Author>> getAuthorById(@PathVariable Integer authorId) {

		// read authors by author id
		List<Author> authors = adminService.readAuthors(authorId, null);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (authors != null && authors.size() > 0) {
			return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}

	}

	@RequestMapping(path = "/lms/admin/authors/name/{authorName}")
	public ResponseEntity<List<Author>> getAuthorByName(@PathVariable String authorName) {

		// read authors by author name
		List<Author> authors = adminService.readAuthors(null, authorName);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (authors != null && authors.size() > 0) {
			return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
		} else {
			// author name not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	//
	//
	// PUBLISHER mappings ----------------------------------------------------------
	//
	//

	@RequestMapping(path = "/lms/admin/publishers")
	public ResponseEntity<List<Publisher>> getAllPublishers() {

		// read all publishers
		List<Publisher> publishers = adminService.readPublishers(null, null);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (publishers != null && publishers.size() > 0) {
			return new ResponseEntity<List<Publisher>>(publishers, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(path = "/lms/admin/publishers/id/{publisherId}")
	public ResponseEntity<List<Publisher>> getPublisherById(@PathVariable Integer publisherId) {

		// read publishers by publisher id
		List<Publisher> publishers = adminService.readPublishers(publisherId, null);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (publishers != null && publishers.size() > 0) {
			return new ResponseEntity<List<Publisher>>(publishers, HttpStatus.OK);
		} else {
			// publisher id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(path = "/lms/admin/publishers/name/{publisherName}")
	public ResponseEntity<List<Publisher>> getPublisherByName(@PathVariable String publisherName) {

		// read publishers by publisher id
		List<Publisher> publishers = adminService.readPublishers(null, publisherName);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (publishers != null && publishers.size() > 0) {
			return new ResponseEntity<List<Publisher>>(publishers, HttpStatus.OK);
		} else {
			// publisher id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	//
	//
	// BORROWER mappings ----------------------------------------------------------
	//
	//

	@RequestMapping(path = "/lms/admin/borrowers")
	public ResponseEntity<List<Borrower>> getAllBorrowers() {

		// read all borrowers
		List<Borrower> borrowers = adminService.readBorrowers(null, null);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (borrowers != null && borrowers.size() > 0) {
			return new ResponseEntity<List<Borrower>>(borrowers, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(path = "/lms/admin/borrowers/cardNo/{borrowerId}")
	public ResponseEntity<List<Borrower>> getBorrowerById(@PathVariable Integer borrowerId) {

		// read borrowers by borrower id
		List<Borrower> borrowers = adminService.readBorrowers(borrowerId, null);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (borrowers != null && borrowers.size() > 0) {
			return new ResponseEntity<List<Borrower>>(borrowers, HttpStatus.OK);
		} else {
			// borrower id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(path = "/lms/admin/borrowers/name/{borrowerName}")
	public ResponseEntity<List<Borrower>> getBorrowerByName(@PathVariable String borrowerName) {

		// read borrowers by borrower id
		List<Borrower> borrowers = adminService.readBorrowers(null, borrowerName);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (borrowers != null && borrowers.size() > 0) {
			return new ResponseEntity<List<Borrower>>(borrowers, HttpStatus.OK);
		} else {
			// borrower id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	//
	//
	// GENRE mappings ----------------------------------------------------------
	//
	//

	@RequestMapping(path = "/lms/admin/genres")
	public ResponseEntity<List<Genre>> getAllGenres() {

		// read all genres
		List<Genre> genres = adminService.readGenres(null, null);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (genres != null && genres.size() > 0) {
			return new ResponseEntity<List<Genre>>(genres, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(path = "/lms/admin/genres/id/{genreId}")
	public ResponseEntity<List<Genre>> getGenreById(@PathVariable Integer genreId) {

		// read genres by genre id
		List<Genre> genres = adminService.readGenres(genreId, null);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (genres != null && genres.size() > 0) {
			return new ResponseEntity<List<Genre>>(genres, HttpStatus.OK);
		} else {
			// genre id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(path = "/lms/admin/genres/name/{genreName}")
	public ResponseEntity<List<Genre>> getGenreByName(@PathVariable String genreName) {

		// read genres by genre id
		List<Genre> genres = adminService.readGenres(null, genreName);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (genres != null && genres.size() > 0) {
			return new ResponseEntity<List<Genre>>(genres, HttpStatus.OK);
		} else {
			// genre id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
}
