package com.ss.training.spring.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ss.training.spring.lms.dao.AuthorDAO;
import com.ss.training.spring.lms.dao.BookDAO;
import com.ss.training.spring.lms.dao.BorrowerDAO;
import com.ss.training.spring.lms.dao.GenreDAO;
import com.ss.training.spring.lms.dao.PublisherDAO;
import com.ss.training.spring.lms.entity.Author;
import com.ss.training.spring.lms.entity.Book;
import com.ss.training.spring.lms.entity.Borrower;
import com.ss.training.spring.lms.entity.Genre;
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
		}  catch (ClassNotFoundException e) {
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
		}  catch (ClassNotFoundException e) {
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
			System.out.println("There is a problem with the SQL in AdminService.readAuthors()");
		}  catch (ClassNotFoundException e) {
			System.out.println("Class not found in AdminService.readAuthors()");
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
			System.out.println("There is a problem with the SQL in AdminService.readAuthors()");
		}  catch (ClassNotFoundException e) {
			System.out.println("Class not found in AdminService.readAuthors()");
		}
		
		return null;

	}
}
