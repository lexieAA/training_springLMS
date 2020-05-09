package com.ss.training.spring.lms.service;

import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;

import com.ss.training.spring.lms.dao.AuthorDAO;
import com.ss.training.spring.lms.dao.BookDAO;
import com.ss.training.spring.lms.dao.BorrowerDAO;
import com.ss.training.spring.lms.dao.GenreDAO;
import com.ss.training.spring.lms.dao.LibraryBranchDAO;
import com.ss.training.spring.lms.dao.PublisherDAO;
import com.ss.training.spring.lms.entity.Author;
import com.ss.training.spring.lms.entity.Book;
import com.ss.training.spring.lms.entity.Borrower;
import com.ss.training.spring.lms.entity.Genre;
import com.ss.training.spring.lms.entity.LibraryBranch;
import com.ss.training.spring.lms.entity.Publisher;

public class AdminService {

	@Autowired
	AuthorDAO aDAO;

	@Autowired
	BookDAO bDAO;

	@Autowired
	BorrowerDAO borrDAO;

	@Autowired
	PublisherDAO pDAO;

	@Autowired
	GenreDAO gDAO;

	@Autowired
	LibraryBranchDAO lbDAO;

	/**
	 * For Author objects. The method provides read operations for passed object of
	 * this type. A ready by primary key, read by name, or read everything operation
	 * can be performed depending on which method parameters are null
	 * 
	 * @param pk         primary key for this object type
	 * @param authorName name for this object type
	 * @return a list of this object type; null on failure
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Author> readAuthors(Integer pk, String authorName) {

		try {

			// perform read operation depending on which object variables are set
			// if primary key is provided, perform a read by primary key
			if (pk != null) {
				List<Author> authors = aDAO.readAuthorById(pk);
				return authors;
			}

			// if a name is provided, perform a search by name
			else if (authorName != null) {
				List<Author> authors = aDAO.readAuthorByName(authorName);
				return authors;
			}

			// otherwise, read everything
			else {
				List<Author> authors = aDAO.readAllAuthors();
				for (Author a : authors) {
					a.setBooks(bDAO.readAllBooksByAuthor(a.getAuthorId()));
				}
				return authors;
			}
		} catch (SQLException e) {
			System.out.println("There is a problem with the SQL in AdminService.readAuthors()");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found in AdminService.readAuthors()");
		}

		return null;

	}

	public List<Publisher> readPublishers(Integer pk, String publisherName) {

		try {

			// perform read operation depending on which object variables are set
			// if primary key is provided, perform a read by primary key
			if (pk != null) {
				List<Publisher> publishers = pDAO.readPublisherById(pk);
				return publishers;
			}

			// if a name is provided, perform a search by name
			else if (publisherName != null) {
				List<Publisher> publishers = pDAO.readPublisherByName(publisherName);
				return publishers;
			}

			// otherwise, read everything
			else {
				List<Publisher> publishers = pDAO.readAllPublishers();
				return publishers;
			}
		} catch (SQLException e) {
			System.out.println("There is a problem with the SQL in AdminService.readAuthors()");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found in AdminService.readAuthors()");
		}

		return null;

	}

	public List<Borrower> readBorrowers(Integer pk, String borrowerName) {

		try {

			// perform read operation depending on which object variables are set
			// if primary key is provided, perform a read by primary key
			if (pk != null) {
				List<Borrower> borrowers = borrDAO.readBorrowerById(pk);
				return borrowers;
			}

			// if a name is provided, perform a search by name
			else if (borrowerName != null) {
				List<Borrower> borrowers = borrDAO.readBorrowerByName(borrowerName);
				return borrowers;
			}

			// otherwise, read everything
			else {
				List<Borrower> borrowers = borrDAO.readAllBorrowers();
				return borrowers;
			}
		} catch (SQLException e) {
			System.out.println("There is a problem with the SQL in AdminService.readBorrowers()");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found in AdminService.readBorrowers()");
		}

		return null;

	}

	public List<Genre> readGenres(Integer pk, String genreName) {

		try {

			// perform read operation depending on which object variables are set
			// if primary key is provided, perform a read by primary key
			if (pk != null) {
				List<Genre> genres = gDAO.readGenresById(pk);
				return genres;
			}

			// if a name is provided, perform a search by name
			else if (genreName != null) {
				List<Genre> genres = gDAO.readGenresByName(genreName);
				return genres;
			}

			// otherwise, read everything
			else {
				List<Genre> genres = gDAO.readAllGenres();
				return genres;
			}
		} catch (SQLException e) {
			System.out.println("There is a problem with the SQL in AdminService.readGenres()");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found in AdminService.readGenres()");
		}

		return null;

	}

	public List<LibraryBranch> readBranches(Integer pk, String branchName) {

		try {

			// perform read operation depending on which object variables are set
			// if primary key is provided, perform a read by primary key
			if (pk != null) {
				List<LibraryBranch> branches = lbDAO.readLibraryBranchById(pk);
				return branches;
			}

			// if a name is provided, perform a search by name
			else if (branchName != null) {
				List<LibraryBranch> branches = lbDAO.readLibraryBranchByName(branchName);
				return branches;
			}

			// otherwise, read everything
			else {
				List<LibraryBranch> branches = lbDAO.readAllLibraryBranches();
				return branches;
			}
		} catch (SQLException e) {
			System.out.println("There is a problem with the SQL in AdminService.readBranches()");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found in AdminService.readBranches()");
		}

		return null;

	}

	public List<Book> readBooks(Integer pk, String bookTitle) {

		try {

			// perform read operation depending on which object variables are set
			// if primary key is provided, perform a read by primary key
			if (pk != null) {
				List<Book> books = bDAO.readBookById(pk);
				return books;
			}

			// if a name is provided, perform a search by name
			else if (bookTitle != null) {
				List<Book> books = bDAO.readBookByTitle(bookTitle);
				return books;
			}

			// otherwise, read everything
			else {
				List<Book> books = bDAO.readAllBooks();
				for (Book b : books) {
					b.setAuthors(aDAO.readAllAuthorsByBookId(b.getBookId()));
					b.setGenres(gDAO.readGenresByBookId(b.getBookId()));
				}
				return books;
			}
		} catch (SQLException e) {
			System.out.println("There is a problem with the SQL in AdminService.readBooks()");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found in AdminService.readBooks()");
		}

		return null;

	}
	
	public void savePublisher(Publisher publisher) throws SQLException {

		try {

			// perform write operation depending on which object variables are set
			// update case where both a key and name are given
			if (publisher.getPublisherId() != null && publisher.getPublisherName() != null) {
				pDAO.updatePublisher(publisher);
			}

			// deletion case when an id is given but no name
			else if (publisher.getPublisherId() != null) {
//				blDAO.deleteBookLoansByPubId(publisher.getPublisherId());
//				bcDAO.deleteBookCopiesByPubId(publisher.getPublisherId());
//				bDAO.deleteBooksByPubId(publisher.getPublisherId());
//				pDAO.deletePublisher(publisher);
//				System.out.println("\nPublisher deleted");
			}

			// insertion case otherwise
			else {
//				pDAO.addPublisher(publisher);
//				System.out.println("Adding " + publisher.getPublisherName() + " to publishers");
			}
			
			// commit transaction and display success message
			pDAO.conn.commit();
		} catch (SQLException e) {
			System.out.println("There is a problem with the SQL in AdminService.readBooks()");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found in AdminService.readBooks()");
		}
	}
}
