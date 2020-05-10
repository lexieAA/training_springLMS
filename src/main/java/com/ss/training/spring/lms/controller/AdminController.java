package com.ss.training.spring.lms.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ss.training.spring.lms.entity.Author;
import com.ss.training.spring.lms.entity.Book;
import com.ss.training.spring.lms.entity.Borrower;
import com.ss.training.spring.lms.entity.Genre;
import com.ss.training.spring.lms.entity.LibraryBranch;
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

	@RequestMapping(path = "/lms/admin/authors", method = RequestMethod.GET)
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

	@RequestMapping(path = "/lms/admin/authors/id/{authorId}", method = RequestMethod.GET)
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

	@RequestMapping(path = "/lms/admin/authors/name/{authorName}", method = RequestMethod.GET)
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

	@RequestMapping(path = "/lms/admin/authors", method = RequestMethod.PUT)
	public ResponseEntity<String> updateAuthor(@RequestBody Author author) {

		// read authors by author id
		try {
			adminService.saveAuthor(author);
		} catch (Exception e) {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	/**
	 * This method handles post requests for the passed object type. This case is
	 * for the creation of a new object. The primary key value should be null in the
	 * passed request object.
	 * 
	 * @param author passed object from client
	 * @return -1 indicating failure; positive Integer indicating success, with the
	 *         value being the key created
	 */
	@RequestMapping(path = "/lms/admin/authors", method = RequestMethod.POST)
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
		} else {
			// success in creating a new record, return created status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.CREATED);
		}
	}
	
	/**
	 * This method handles delete requests for the specified id.
	 * 
	 * @param authorId id of object to delete
	 * @return BAD_REQUEST indicating failure when id delete not found; OK
	 *         indicating success
	 */
	@RequestMapping(path = "/lms/admin/authors/id/{authorId}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deleteAuthor(@PathVariable Integer authorId) {

		Integer returnInt = -1; // for determining HttpStatus
		
		// create an object with an id and null name to indicate a delete in saveAuthor()
		Author author = new Author();
		author.setAuthorId(authorId);
		returnInt = adminService.saveAuthor(author);

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else {
			// success in creating a new record, return created status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.OK);
		}
	}

	//
	//
	// PUBLISHER mappings ----------------------------------------------------------
	//
	//

	@RequestMapping(path = "/lms/admin/publishers", method = RequestMethod.GET)
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

	@RequestMapping(path = "/lms/admin/publishers/id/{publisherId}", method = RequestMethod.GET)
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

	@RequestMapping(path = "/lms/admin/publishers/name/{publisherName}", method = RequestMethod.GET)
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

	@RequestMapping(path = "/lms/admin/publishers", method = RequestMethod.PUT)
	public ResponseEntity<String> updatePublisher(@RequestBody Publisher publisher) {

		// read publishers by publisher id
		try {
			adminService.savePublisher(publisher);
		} catch (Exception e) {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	/**
	 * This method handles post requests for the passed object type. This case is
	 * for the creation of a new object. The primary key value should be null in the
	 * passed request object.
	 * 
	 * @param publisher passed object from client
	 * @return -1 indicating failure; positive Integer indicating success, with the
	 *         value being the key created
	 */
	@RequestMapping(path = "/lms/admin/publishers", method = RequestMethod.POST)
	public ResponseEntity<Integer> createPublisher(@RequestBody Publisher publisher) {

		Integer returnInt = -1; // for determining HttpStatus

		// create new publisher if request object contains the neccessary information
		if (publisher != null && publisher.getPublisherId() == null && publisher.getPublisherName() != null
				&& publisher.getPublisherAddress() != null && publisher.getPublisherPhone() != null) {
			returnInt = adminService.savePublisher(publisher);
		}

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else {
			// success in creating a new record, return created status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.CREATED);
		}
	}
	
	/**
	 * This method handles delete requests for the specified id.
	 * 
	 * @param publisherId id of object to delete
	 * @return BAD_REQUEST indicating failure when id delete not found; OK
	 *         indicating success
	 */
	@RequestMapping(path = "/lms/admin/publishers/id/{publisherId}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deletePublisher(@PathVariable Integer publisherId) {

		Integer returnInt = -1; // for determining HttpStatus
		
		// create an object with an id and null name to indicate a delete in savePublisher()
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		returnInt = adminService.savePublisher(publisher);

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else {
			// success in creating a new record, return created status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.OK);
		}
	}

	//
	//
	// BORROWER mappings ----------------------------------------------------------
	//
	//

	@RequestMapping(path = "/lms/admin/borrowers", method = RequestMethod.GET)
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

	@RequestMapping(path = "/lms/admin/borrowers/cardNo/{borrowerId}", method = RequestMethod.GET)
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

	@RequestMapping(path = "/lms/admin/borrowers/name/{borrowerName}", method = RequestMethod.GET)
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

	@RequestMapping(path = "/lms/admin/borrowers", method = RequestMethod.PUT)
	public ResponseEntity<String> updateBorrower(@RequestBody Borrower borrower) {

		// read borrowers by borrower id
		try {
			adminService.saveBorrower(borrower);
		} catch (Exception e) {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	/**
	 * This method handles post requests for the passed object type. This case is
	 * for the creation of a new object. The primary key value should be null in the
	 * passed request object.
	 * 
	 * @param borrower passed object from client
	 * @return -1 indicating failure; positive Integer indicating success, with the
	 *         value being the key created
	 */
	@RequestMapping(path = "/lms/admin/borrowers", method = RequestMethod.POST)
	public ResponseEntity<Integer> createBorrower(@RequestBody Borrower borrower) {

		Integer returnInt = -1; // for determining HttpStatus

		// create new borrower if request object contains the necessary information
		if (borrower != null && borrower.getCardNo() == null && borrower.getBorrowerName() != null
				&& borrower.getBorrowerAddress() != null && borrower.getBorrowerPhone() != null) {
			returnInt = adminService.saveBorrower(borrower);
		}

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else {
			// success in creating a new record, return created status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.CREATED);
		}
	}
	
	/**
	 * This method handles delete requests for the specified id.
	 * 
	 * @param cardNo id of object to delete
	 * @return BAD_REQUEST indicating failure when id delete not found; OK
	 *         indicating success
	 */
	@RequestMapping(path = "/lms/admin/borrowers/cardNo/{cardNo}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deleteBorrower(@PathVariable Integer cardNo) {

		Integer returnInt = -1; // for determining HttpStatus
		
		// create an object with an id and null name to indicate a delete in saveBorrower()
		Borrower borrower = new Borrower();
		borrower.setCardNo(cardNo);
		returnInt = adminService.saveBorrower(borrower);

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else {
			// success in creating a new record, return created status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.OK);
		}
	}

	//
	//
	// GENRE mappings ----------------------------------------------------------
	//
	//

	@RequestMapping(path = "/lms/admin/genres", method = RequestMethod.GET)
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

	@RequestMapping(path = "/lms/admin/genres/id/{genreId}", method = RequestMethod.GET)
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

	@RequestMapping(path = "/lms/admin/genres/name/{genreName}", method = RequestMethod.GET)
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

	@RequestMapping(path = "/lms/admin/genres", method = RequestMethod.PUT)
	public ResponseEntity<String> updateGenre(@RequestBody Genre genre) {

		// read genres by genre id
		try {
			adminService.saveGenre(genre);
		} catch (Exception e) {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	/**
	 * This method handles post requests for the passed object type. This case is
	 * for the creation of a new object. The primary key value should be null in the
	 * passed request object.
	 * 
	 * @param genre passed object from client
	 * @return -1 indicating failure; positive Integer indicating success, with the
	 *         value being the key created
	 */
	@RequestMapping(path = "/lms/admin/genres", method = RequestMethod.POST)
	public ResponseEntity<Integer> createGenre(@RequestBody Genre genre) {

		Integer returnInt = -1; // for determining HttpStatus

		// create new genre if request object contains the neccessary information
		if (genre != null && genre.getGenreId() == null && genre.getGenreName() != null) {
			returnInt = adminService.saveGenre(genre);
		}

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else {
			// success in creating a new record, return created status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.CREATED);
		}
	}
	
	/**
	 * This method handles delete requests for the specified id.
	 * 
	 * @param genreId id of object to delete
	 * @return BAD_REQUEST indicating failure when id delete not found; OK
	 *         indicating success
	 */
	@RequestMapping(path = "/lms/admin/genres/id/{genreId}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deleteGenre(@PathVariable Integer genreId) {

		Integer returnInt = -1; // for determining HttpStatus
		
		// create an object with an id and null name to indicate a delete in saveGenre()
		Genre genre = new Genre();
		genre.setGenreId(genreId);
		returnInt = adminService.saveGenre(genre);

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else {
			// success in creating a new record, return created status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.OK);
		}
	}

	//
	//
	// LIBRARY BRANCH mappings
	// ----------------------------------------------------------
	//
	//

	@RequestMapping(path = "/lms/admin/branches", method = RequestMethod.GET)
	public ResponseEntity<List<LibraryBranch>> getAllBranches() {

		// read all branches
		List<LibraryBranch> branches = adminService.readBranches(null, null);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (branches != null && branches.size() > 0) {
			return new ResponseEntity<List<LibraryBranch>>(branches, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(path = "/lms/admin/branches/id/{branchId}", method = RequestMethod.GET)
	public ResponseEntity<List<LibraryBranch>> getBranchById(@PathVariable Integer branchId) {

		// read branches by branch id
		List<LibraryBranch> branches = adminService.readBranches(branchId, null);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (branches != null && branches.size() > 0) {
			return new ResponseEntity<List<LibraryBranch>>(branches, HttpStatus.OK);
		} else {
			// branch id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(path = "/lms/admin/branches/name/{branchName}", method = RequestMethod.GET)
	public ResponseEntity<List<LibraryBranch>> getBranchByName(@PathVariable String branchName) {

		// read branches by branch id
		List<LibraryBranch> branches = adminService.readBranches(null, branchName);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (branches != null && branches.size() > 0) {
			return new ResponseEntity<List<LibraryBranch>>(branches, HttpStatus.OK);
		} else {
			// branch id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(path = "/lms/admin/branches", method = RequestMethod.PUT)
	public ResponseEntity<String> updateBranch(@RequestBody LibraryBranch branch) {

		// read branches by branch id
		try {
			adminService.saveBranch(branch);
		} catch (Exception e) {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	/**
	 * This method handles post requests for the passed object type. This case is
	 * for the creation of a new object. The primary key value should be null in the
	 * passed request object.
	 * 
	 * @param branch passed object from client
	 * @return -1 indicating failure; positive Integer indicating success, with the
	 *         value being the key created
	 */
	@RequestMapping(path = "/lms/admin/branches", method = RequestMethod.POST)
	public ResponseEntity<Integer> createBranch(@RequestBody LibraryBranch branch) {

		Integer returnInt = -1; // for determining HttpStatus

		// create new branch if request object contains the necessary information
		if (branch != null && branch.getBranchId() == null && branch.getBranchName() != null
				&& branch.getBranchAddress() != null) {
			returnInt = adminService.saveBranch(branch);
		}

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else {
			// success in creating a new record, return created status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.CREATED);
		}
	}
	
	/**
	 * This method handles delete requests for the specified id.
	 * 
	 * @param branchId id of object to delete
	 * @return BAD_REQUEST indicating failure when id delete not found; OK
	 *         indicating success
	 */
	@RequestMapping(path = "/lms/admin/branches/id/{branchId}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deleteBranch(@PathVariable Integer branchId) {

		Integer returnInt = -1; // for determining HttpStatus
		
		// create an object with an id and null name to indicate a delete in saveBranch()
		LibraryBranch branch = new LibraryBranch();
		branch.setBranchId(branchId);
		returnInt = adminService.saveBranch(branch);

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else {
			// success in creating a new record, return created status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.OK);
		}
	}

	//
	//
	// BOOK mappings ----------------------------------------------------------
	//
	//

	@RequestMapping(path = "/lms/admin/books", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> getAllBooks() {

		// read all books
		List<Book> books = adminService.readBooks(null, null);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (books != null && books.size() > 0) {
			return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(path = "/lms/admin/books/id/{bookId}", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> getBookById(@PathVariable Integer bookId) {

		// read books by book id
		List<Book> books = adminService.readBooks(bookId, null);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (books != null && books.size() > 0) {
			return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
		} else {
			// branch id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(path = "/lms/admin/books/title/{bookTitle}", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> getBookByTitle(@PathVariable String bookTitle) {

		// read books by book id
		List<Book> books = adminService.readBooks(null, bookTitle);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (books != null && books.size() > 0) {
			return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
		} else {
			// branch id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(path = "/lms/admin/books", method = RequestMethod.PUT)
	public ResponseEntity<String> updateBook(@RequestBody Book book) {

		// read books by book id
		try {
			adminService.saveBook(book);
		} catch (Exception e) {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	/**
	 * This method handles post requests for the passed object type. This case is
	 * for the creation of a new object. The primary key value should be null in the
	 * passed request object.
	 * 
	 * @param book passed object from client
	 * @return -1 indicating failure; positive Integer indicating success, with the
	 *         value being the key created
	 */
	@RequestMapping(path = "/lms/admin/books", method = RequestMethod.POST)
	public ResponseEntity<Integer> createBook(@RequestBody Book book) {

		Integer returnInt = -1; // for determining HttpStatus

		// create new book if request object contains the necessary information
		if (book != null && book.getBookId() == null && book.getTitle() != null && book.getPublisherId() != null
				&& book.getAuthors() != null) {
			returnInt = adminService.saveBook(book);
		}

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else {
			// success in creating a new record, return created status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.CREATED);
		}
	}

	/**
	 * This method handles delete requests for the specified id.
	 * 
	 * @param bookId id of object to delete
	 * @return BAD_REQUEST indicating failure when id delete not found; OK
	 *         indicating success
	 */
	@RequestMapping(path = "/lms/admin/books/id/{bookId}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deleteBook(@PathVariable Integer bookId) {

		Integer returnInt = -1; // for determining HttpStatus
		
		// create an object with an id and null name to indicate a delete in saveBook()
		Book book = new Book();
		book.setBookId(bookId);
		returnInt = adminService.saveBook(book);

		// check returnInt to select proper HttpStatus to respond with
		if (returnInt == -1) {
			// there was a failure in the transaction, return a BAD_REQUEST status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.BAD_REQUEST);
		} else {
			// success in creating a new record, return created status
			return new ResponseEntity<Integer>(returnInt, HttpStatus.OK);
		}
	}
}
