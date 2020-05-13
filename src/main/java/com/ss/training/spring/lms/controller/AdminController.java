package com.ss.training.spring.lms.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ss.training.spring.lms.entity.Author;
import com.ss.training.spring.lms.entity.Book;
import com.ss.training.spring.lms.entity.BookLoan;
import com.ss.training.spring.lms.entity.BookLoansKey;
import com.ss.training.spring.lms.entity.Borrower;
import com.ss.training.spring.lms.entity.Genre;
import com.ss.training.spring.lms.entity.LibraryBranch;
import com.ss.training.spring.lms.entity.Publisher;
import com.ss.training.spring.lms.service.AdminService;

@RestController
@RequestMapping("/lms/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	//
	//
	// AUTHOR mappings ----------------------------------------------------------
	//
	//

	/**
	 * This method handles read all requests
	 * 
	 * @return NOT_FOUND if no results; OK indicating success
	 */
	@GetMapping(path = "/authors", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Author>> getAllAuthors() {
		// read all authors
		List<Author> authors = adminService.findAllAuthors();

		// a successful request should produce a list not null with a size greater than
		// zero
		if (authors != null && authors.size() > 0) {
			return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This method handles read requests for the specified id
	 * 
	 * @param authorId id to find
	 * @return NOT_FOUND if no results; OK indicating success
	 */
	@GetMapping(path = "/authors/id/{authorId}")
	public ResponseEntity<Author> getAuthorById(@PathVariable Long authorId) {

		// read authors by author id
		Optional<Author> author = adminService.findByAuthorId(authorId);

		// check if result found, and send response accordingly
		if (author.isPresent()) {
			return new ResponseEntity<Author>(author.get(), HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This method handles read requests for the specified name
	 * 
	 * @param authorName name to find
	 * @return NOT_FOUND if no results; OK indicating success
	 */
	@GetMapping(path = "/authors/name/{authorName}")
	public ResponseEntity<List<Author>> getAuthorByName(@PathVariable String authorName) {

		// read authors by author id
		List<Author> authors = adminService.findByAuthorName(authorName);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (authors != null && authors.size() > 0) {
			return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This method handles update requests for the specified id
	 * 
	 * @param author object to update
	 * @return BAD_REQUEST if passed an invalid author id; OK if record is found and
	 *         updated
	 */
	@PutMapping(path = "/authors", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> updateAuthor(@RequestBody Author author) {

		Integer returnInt = -1; // for determining HttpStatus

		// read authors by author id
		returnInt = adminService.saveAuthor(author);

		// indicate success or failure
		if (returnInt == 0) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * This method handles post requests for the passed object type. This case is
	 * for the creation of a new object. The primary key value should be null in the
	 * passed request object.
	 * 
	 * @param author passed object from client
	 * @return BAD_REQUEST (-1) indicating failure; CONFLICT (1) indicating
	 *         duplicate; CREATED (0) indicating success
	 */
	@PostMapping(path = "/authors", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Integer> createAuthor(@RequestBody Author author) {

		Integer returnInt = -1; // for determining HttpStatus

		// create new author if request object contains the necessary information
		if (author != null && author.getAuthorId() == null && author.getAuthorName() != null) {
			returnInt = adminService.saveAuthor(author);
		}

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else if (returnInt == 1) {
			// already exists, return conflict status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.CONFLICT);
		} else {
			// success in creating a new record, return created status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.CREATED);
		}
	}

	/**
	 * This method handles delete requests for the specified id.
	 * 
	 * @param authorId id of object to delete
	 * @return BAD_REQUEST (-1) indicating query failure; NOT_FOUND (1) indicating
	 *         id to delete not found; OK (0) indicating successful deletion
	 */
	@DeleteMapping(path = "/authors/id/{authorId}")
	public ResponseEntity<Integer> deleteAuthor(@PathVariable Long authorId) {

		Integer returnInt = -1; // for determining HttpStatus

		// create an object with an id and null name to indicate a delete in
		// saveAuthor()
		Author author = new Author();
		author.setAuthorId(authorId);
		author.setAuthorName(null);
		returnInt = adminService.saveAuthor(author);

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else if (returnInt == 1) {
			// id to delete not found
			return new ResponseEntity<Integer>(returnInt, HttpStatus.NOT_FOUND);
		} else {
			// success in deleting record, return ok status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.OK);
		}
	}

	//
	//
	// PUBLISHER mappings ----------------------------------------------------------
	//
	//

	/**
	 * This method handles read all requests
	 * 
	 * @return NOT_FOUND if no results; OK indicating success
	 */
	@GetMapping(path = "/publishers")
	public ResponseEntity<List<Publisher>> getAllPublishers() {

		// read all publishers
		List<Publisher> publishers = adminService.findAllPublishers();

		// a successful request should produce a list not null with a size greater than
		// zero
		if (publishers != null && publishers.size() > 0) {
			return new ResponseEntity<List<Publisher>>(publishers, HttpStatus.OK);
		} else {
			// publisher id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This method handles read requests for the specified id
	 * 
	 * @param publisherId id to find
	 * @return NOT_FOUND if no results; OK indicating success
	 */
	@GetMapping(path = "/publishers/id/{publisherId}")
	public ResponseEntity<Publisher> getPublisherById(@PathVariable Long publisherId) {

		// read publishers by publisher id
		Optional<Publisher> publisher = adminService.findByPublisherId(publisherId);

		// check if result found, and send response accordingly
		if (publisher.isPresent()) {
			return new ResponseEntity<Publisher>(publisher.get(), HttpStatus.OK);
		} else {
			// publisher id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This method handles read requests for the specified name
	 * 
	 * @param publisherName name to find
	 * @return NOT_FOUND if no results; OK indicating success
	 */
	@GetMapping(path = "/publishers/name/{publisherName}")
	public ResponseEntity<List<Publisher>> getPublisherByName(@PathVariable String publisherName) {

		// read publishers by publisher id
		List<Publisher> publishers = adminService.findByPublisherName(publisherName);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (publishers != null && publishers.size() > 0) {
			return new ResponseEntity<List<Publisher>>(publishers, HttpStatus.OK);
		} else {
			// publisher id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This method handles update requests for the specified id
	 * 
	 * @param publisher object to update
	 * @return BAD_REQUEST if passed an invalid publisher id; OK if record is found
	 *         and updated
	 */
	@PutMapping(path = "/publishers", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> updatePublisher(@RequestBody Publisher publisher) {

		Integer returnInt = -1; // for determining HttpStatus

		// read publishers by publisher id
		returnInt = adminService.savePublisher(publisher);

		// indicate success or failure
		if (returnInt == 0) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * This method handles post requests for the passed object type. This case is
	 * for the creation of a new object. The primary key value should be null in the
	 * passed request object.
	 * 
	 * @param publisher passed object from client
	 * @return BAD_REQUEST (-1) indicating failure; CONFLICT (1) indicating
	 *         duplicate; CREATED (0) indicating success
	 */
	@PostMapping(path = "/publishers", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Integer> createPublisher(@RequestBody Publisher publisher) {

		Integer returnInt = -1; // for determining HttpStatus

		// create new publisher if request object contains the necessary information
		if (publisher != null && publisher.getPublisherId() == null && publisher.getPublisherName() != null) {
			returnInt = adminService.savePublisher(publisher);
		}

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else if (returnInt == 1) {
			// already exists, return conflict status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.CONFLICT);
		} else {
			// success in creating a new record, return created status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.CREATED);
		}
	}

	/**
	 * This method handles delete requests for the specified id.
	 * 
	 * @param publisherId id of object to delete
	 * @return BAD_REQUEST (-1) indicating query failure; NOT_FOUND (1) indicating
	 *         id to delete not found; OK (0) indicating successful deletion
	 */
	@DeleteMapping(path = "/publishers/id/{publisherId}")
	public ResponseEntity<Integer> deletePublisher(@PathVariable Long publisherId) {

		Integer returnInt = -1; // for determining HttpStatus

		// create an object with an id and null name to indicate a delete in
		// savePublisher()
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		publisher.setPublisherName(null);
		returnInt = adminService.savePublisher(publisher);

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else if (returnInt == 1) {
			// id to delete not found
			return new ResponseEntity<Integer>(returnInt, HttpStatus.NOT_FOUND);
		} else {
			// success in deleting record, return ok status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.OK);
		}
	}

	//
	//
	// BORROWER mappings ----------------------------------------------------------
	//
	//

	/**
	 * This method handles read all requests
	 * 
	 * @return NOT_FOUND if no results; OK indicating success
	 */
	@GetMapping(path = "/borrowers")
	public ResponseEntity<List<Borrower>> getAllBorrowers() {

		// read all borrowers
		List<Borrower> borrowers = adminService.findAllBorrowers();

		// a successful request should produce a list not null with a size greater than
		// zero
		if (borrowers != null && borrowers.size() > 0) {
			return new ResponseEntity<List<Borrower>>(borrowers, HttpStatus.OK);
		} else {
			// borrower id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This method handles read requests for the specified id
	 * 
	 * @param borrowerId id to find
	 * @return NOT_FOUND if no results; OK indicating success
	 */
	@GetMapping(path = "/borrowers/cardno/{cardNo}")
	public ResponseEntity<Borrower> getBorrowerById(@PathVariable Long cardNo) {

		// read borrowers by borrower id
		Optional<Borrower> borrower = adminService.findByCardNo(cardNo);

		// check if result found, and send response accordingly
		if (borrower.isPresent()) {
			return new ResponseEntity<Borrower>(borrower.get(), HttpStatus.OK);
		} else {
			// borrower id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This method handles read requests for the specified name
	 * 
	 * @param borrowerName name to find
	 * @return NOT_FOUND if no results; OK indicating success
	 */
	@GetMapping(path = "/borrowers/name/{borrowerName}")
	public ResponseEntity<List<Borrower>> getBorrowerByName(@PathVariable String borrowerName) {

		// read borrowers by borrower id
		List<Borrower> borrowers = adminService.findByBorrowerName(borrowerName);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (borrowers != null && borrowers.size() > 0) {
			return new ResponseEntity<List<Borrower>>(borrowers, HttpStatus.OK);
		} else {
			// borrower id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This method handles update requests for the specified id
	 * 
	 * @param borrower object to update
	 * @return BAD_REQUEST if passed an invalid card no; OK if record is found and
	 *         updated
	 */
	@PutMapping(path = "/borrowers", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> updateBorrower(@RequestBody Borrower borrower) {

		Integer returnInt = -1; // for determining HttpStatus

		// read borrowers by borrower cardno
		returnInt = adminService.saveBorrower(borrower);

		// indicate success or failure
		if (returnInt == 0) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * This method handles post requests for the passed object type. This case is
	 * for the creation of a new object. The primary key value should be null in the
	 * passed request object.
	 * 
	 * @param borrower passed object from client
	 * @return BAD_REQUEST (-1) indicating failure; CONFLICT (1) indicating
	 *         duplicate; CREATED (0) indicating success
	 */
	@PostMapping(path = "/borrowers", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Integer> createBorrower(@RequestBody Borrower borrower) {

		Integer returnInt = -1; // for determining HttpStatus

		// create new borrower if request object contains the necessary information
		if (borrower != null && borrower.getCardNo() == null && borrower.getBorrowerName() != null) {
			returnInt = adminService.saveBorrower(borrower);
		}

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else if (returnInt == 1) {
			// already exists, return conflict status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.CONFLICT);
		} else {
			// success in creating a new record, return created status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.CREATED);
		}
	}

	/**
	 * This method handles delete requests for the specified id.
	 * 
	 * @param cardNo id of object to delete
	 * @return BAD_REQUEST (-1) indicating query failure; NOT_FOUND (1) indicating
	 *         id to delete not found; OK (0) indicating successful deletion
	 */
	@DeleteMapping(path = "/borrowers/cardno/{cardNo}")
	public ResponseEntity<Integer> deleteBorrower(@PathVariable Long cardNo) {

		Integer returnInt = -1; // for determining HttpStatus

		// create an object with an id and null name to indicate a delete in
		// saveBorrower()
		Borrower borrower = new Borrower();
		borrower.setCardNo(cardNo);
		borrower.setBorrowerName(null);
		returnInt = adminService.saveBorrower(borrower);

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else if (returnInt == 1) {
			// id to delete not found
			return new ResponseEntity<Integer>(returnInt, HttpStatus.NOT_FOUND);
		} else {
			// success in deleting record, return ok status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.OK);
		}
	}

	//
	//
	// GENRE mappings ----------------------------------------------------------
	//
	//

	/**
	 * This method handles read all requests
	 * 
	 * @return NOT_FOUND if no results; OK indicating success
	 */
	@GetMapping(path = "/genres")
	public ResponseEntity<List<Genre>> getAllGenres() {

		// read all genres
		List<Genre> genres = adminService.findAllGenres();

		// a successful request should produce a list not null with a size greater than
		// zero
		if (genres != null && genres.size() > 0) {
			return new ResponseEntity<List<Genre>>(genres, HttpStatus.OK);
		} else {
			// genre id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This method handles read requests for the specified id
	 * 
	 * @param genreId id to find
	 * @return NOT_FOUND if no results; OK indicating success
	 */
	@GetMapping(path = "/genres/id/{genreId}")
	public ResponseEntity<Genre> getGenreById(@PathVariable Long genreId) {

		// read genres by genre id
		Optional<Genre> genre = adminService.findByGenreId(genreId);

		// check if result found, and send response accordingly
		if (genre.isPresent()) {
			return new ResponseEntity<Genre>(genre.get(), HttpStatus.OK);
		} else {
			// genre id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This method handles read requests for the specified name
	 * 
	 * @param genreName name to find
	 * @return NOT_FOUND if no results; OK indicating success
	 */
	@GetMapping(path = "/genres/name/{genreName}")
	public ResponseEntity<List<Genre>> getGenreByName(@PathVariable String genreName) {

		// read genres by genre id
		List<Genre> genres = adminService.findByGenreName(genreName);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (genres != null && genres.size() > 0) {
			return new ResponseEntity<List<Genre>>(genres, HttpStatus.OK);
		} else {
			// genre id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This method handles update requests for the specified id
	 * 
	 * @param genre object to update
	 * @return BAD_REQUEST if passed an invalid card no; OK if record is found and
	 *         updated
	 */
	@PutMapping(path = "/genres", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> updateGenre(@RequestBody Genre genre) {

		Integer returnInt = -1; // for determining HttpStatus

		// read genres by genre cardno
		returnInt = adminService.saveGenre(genre);

		// indicate success or failure
		if (returnInt == 0) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * This method handles post requests for the passed object type. This case is
	 * for the creation of a new object. The primary key value should be null in the
	 * passed request object.
	 * 
	 * @param genre passed object from client
	 * @return BAD_REQUEST (-1) indicating failure; CONFLICT (1) indicating
	 *         duplicate; CREATED (0) indicating success
	 */
	@PostMapping(path = "/genres", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Integer> createGenre(@RequestBody Genre genre) {

		Integer returnInt = -1; // for determining HttpStatus

		// create new genre if request object contains the necessary information
		if (genre != null && genre.getGenreId() == null && genre.getGenreName() != null) {
			returnInt = adminService.saveGenre(genre);
		}

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else if (returnInt == 1) {
			// already exists, return conflict status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.CONFLICT);
		} else {
			// success in creating a new record, return created status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.CREATED);
		}
	}

	/**
	 * This method handles delete requests for the specified id.
	 * 
	 * @param genreId id of object to delete
	 * @return BAD_REQUEST (-1) indicating query failure; NOT_FOUND (1) indicating
	 *         id to delete not found; OK (0) indicating successful deletion
	 */
	@DeleteMapping(path = "/genres/id/{genreId}")
	public ResponseEntity<Integer> deleteGenre(@PathVariable Long genreId) {

		Integer returnInt = -1; // for determining HttpStatus

		// create an object with an id and null name to indicate a delete in saveGenre()
		Genre genre = new Genre();
		genre.setGenreId(genreId);
		genre.setGenreName(null);
		returnInt = adminService.saveGenre(genre);

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else if (returnInt == 1) {
			// id to delete not found
			return new ResponseEntity<Integer>(returnInt, HttpStatus.NOT_FOUND);
		} else {
			// success in deleting record, return ok status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.OK);
		}
	}

	//
	//
	// LIBRARY BRANCH mappings
	// ----------------------------------------------------------
	//
	//

	/**
	 * This method handles read all requests
	 * 
	 * @return NOT_FOUND if no results; OK indicating success
	 */
	@GetMapping(path = "/branches")
	public ResponseEntity<List<LibraryBranch>> getAllBranches() {

		// read all branches
		List<LibraryBranch> branches = adminService.findAllBranches();

		// a successful request should produce a list not null with a size greater than
		// zero
		if (branches != null && branches.size() > 0) {
			return new ResponseEntity<List<LibraryBranch>>(branches, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This method handles read requests for the specified id
	 * 
	 * @param branchId id to find
	 * @return NOT_FOUND if no results; OK indicating success
	 */
	@GetMapping(path = "/branches/id/{branchId}")
	public ResponseEntity<LibraryBranch> getBranchById(@PathVariable Long branchId) {

		// read branches by branch id
		Optional<LibraryBranch> branch = adminService.findByBranchId(branchId);

		// check if result found, and send response accordingly
		if (branch.isPresent()) {
			return new ResponseEntity<LibraryBranch>(branch.get(), HttpStatus.OK);
		} else {
			// branch id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This method handles read requests for the specified name
	 * 
	 * @param branchName name to find
	 * @return NOT_FOUND if no results; OK indicating success
	 */
	@GetMapping(path = "/branches/name/{branchName}")
	public ResponseEntity<List<LibraryBranch>> getBranchByName(@PathVariable String branchName) {

		// read branches by branch id
		List<LibraryBranch> branches = adminService.findByBranchName(branchName);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (branches != null && branches.size() > 0) {
			return new ResponseEntity<List<LibraryBranch>>(branches, HttpStatus.OK);
		} else {
			// branch id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This method handles update requests for the specified id
	 * 
	 * @param branch object to update
	 * @return BAD_REQUEST if passed an invalid card no; OK if record is found and
	 *         updated
	 */
	@PutMapping(path = "/branches", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> updateLibraryBranch(@RequestBody LibraryBranch branch) {

		Integer returnInt = -1; // for determining HttpStatus

		// read LibraryBranches by branch id
		returnInt = adminService.saveLibraryBranch(branch);

		// indicate success or failure
		if (returnInt == 0) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * This method handles post requests for the passed object type. This case is
	 * for the creation of a new object. The primary key value should be null in the
	 * passed request object.
	 * 
	 * @param branch passed object from client
	 * @return BAD_REQUEST (-1) indicating failure; CONFLICT (1) indicating
	 *         duplicate; CREATED (0) indicating success
	 */
	@PostMapping(path = "/branches", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Integer> createBranch(@RequestBody LibraryBranch branch) {

		Integer returnInt = -1; // for determining HttpStatus

		// create new branch if request object contains the necessary information
		if (branch != null && branch.getBranchId() == null && branch.getBranchName() != null) {
			returnInt = adminService.saveLibraryBranch(branch);
		}

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else if (returnInt == 1) {
			// already exists, return conflict status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.CONFLICT);
		} else {
			// success in creating a new record, return created status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.CREATED);
		}
	}

	/**
	 * This method handles delete requests for the specified id.
	 * 
	 * @param branchId id of object to delete
	 * @return BAD_REQUEST (-1) indicating query failure; NOT_FOUND (1) indicating
	 *         id to delete not found; OK (0) indicating successful deletion
	 */
	@DeleteMapping(path = "/branches/id/{branchId}")
	public ResponseEntity<Integer> deleteLibaryBranch(@PathVariable Long branchId) {

		Integer returnInt = -1; // for determining HttpStatus

		// create an object with an id and null name to indicate a delete in
		// saveLibaryBranch()
		LibraryBranch branch = new LibraryBranch();
		branch.setBranchId(branchId);
		branch.setBranchName(null);
		returnInt = adminService.saveLibraryBranch(branch);

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else if (returnInt == 1) {
			// id to delete not found
			return new ResponseEntity<Integer>(returnInt, HttpStatus.NOT_FOUND);
		} else {
			// success in deleting record, return ok status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.OK);
		}
	}

	//
	//
	// BOOK mappings ----------------------------------------------------------
	//
	//

	/**
	 * This method handles read all requests
	 * 
	 * @return NOT_FOUND if no results; OK indicating success
	 */
	@GetMapping(path = "/books")
	public ResponseEntity<List<Book>> getAllBooks() {

		// read all books
		List<Book> books = adminService.findAllBooks();

		// a successful request should produce a list not null with a size greater than
		// zero
		if (books != null && books.size() > 0) {
			return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
		} else {
			// book id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This method handles read requests for the specified id
	 * 
	 * @param bookId id to find
	 * @return NOT_FOUND if no results; OK indicating success
	 */
	@GetMapping(path = "/books/id/{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {

		// read books by book id
		Optional<Book> book = adminService.findByBookId(bookId);

		// check if result found, and send response accordingly
		if (book.isPresent()) {
			return new ResponseEntity<Book>(book.get(), HttpStatus.OK);
		} else {
			// book id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This method handles read requests for the specified name
	 * 
	 * @param bookName name to find
	 * @return NOT_FOUND if no results; OK indicating success
	 */
	@GetMapping(path = "/books/title/{title}")
	public ResponseEntity<List<Book>> getBookByName(@PathVariable String title) {

		// read books by book id
		List<Book> books = adminService.findByBookTitle(title);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (books != null && books.size() > 0) {
			return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
		} else {
			// book id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This method handles update requests for the specified id
	 * 
	 * @param book object to update
	 * @return BAD_REQUEST if passed an invalid card no; OK if record is found and
	 *         updated
	 */
	@PutMapping(path = "/books", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> updateBook(@RequestBody Book book) {

		Integer returnInt = -1; // for determining HttpStatus

		// read books by book id
		returnInt = adminService.saveBook(book);

		// indicate success or failure
		if (returnInt == 0) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * This method handles post requests for the passed object type. This case is
	 * for the creation of a new object. The primary key value should be null in the
	 * passed request object.
	 * 
	 * @param book passed object from client
	 * @return BAD_REQUEST (-1) indicating failure; CONFLICT (1) indicating
	 *         duplicate; CREATED (0) indicating success
	 */
	@PostMapping(path = "/books", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Integer> createBook(@RequestBody Book book) {

		Integer returnInt = -1; // for determining HttpStatus

		// create new book if request object contains the necessary information
		if (book != null && book.getBookId() == null && book.getTitle() != null) {
			returnInt = adminService.saveBook(book);
		}

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else if (returnInt == 1) {
			// already exists, return conflict status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.CONFLICT);
		} else {
			// success in creating a new record, return created status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.CREATED);
		}
	}

	/**
	 * This method handles delete requests for the specified id.
	 * 
	 * @param bookId id of object to delete
	 * @return BAD_REQUEST (-1) indicating query failure; NOT_FOUND (1) indicating
	 *         id to delete not found; OK (0) indicating successful deletion
	 */
	@DeleteMapping(path = "/books/id/{bookId}")
	public ResponseEntity<Integer> deleteBook(@PathVariable Long bookId) {

		Integer returnInt = -1; // for determining HttpStatus

		// create an object with an id and null name to indicate a delete in saveBook()
		Book book = new Book();
		book.setBookId(bookId);
		book.setTitle(null);
		returnInt = adminService.saveBook(book);

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else if (returnInt == 1) {
			// id to delete not found
			return new ResponseEntity<Integer>(returnInt, HttpStatus.NOT_FOUND);
		} else {
			// success in deleting record, return ok status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.OK);
		}
	}

	//
	//
	// BOOKLOAN mappings ----------------------------------------------------------
	//
	//

	/**
	 * This method takes an open loan and extends its due date by 7 days
	 * 
	 * @param loan book loan to extend due date by 7 days
	 * @return BAD_REQUEST (-1) indicating query failure; NOT_FOUND (1) indicating
	 *         id to extend not found; OK (0) indicating successful extension
	 */
	@PutMapping(path = "/loans", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Integer> extendLoan(@RequestBody BookLoansKey loan) {

		Integer returnInt = -1; // for determining HttpStatus

		// call loan extension function and get response
		if (loan != null && loan.getBookId() != null && loan.getBranchId() != null && loan.getCardNo() != null
				&& loan.getDateOut() != null) {
			returnInt = adminService.extendBookLoan(loan);
		}

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else if (returnInt == 1) {
			// loan to extend not found
			return new ResponseEntity<Integer>(returnInt, HttpStatus.NOT_FOUND);
		} else {
			// success in extending record, return ok status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.OK);
		}
	}
}
