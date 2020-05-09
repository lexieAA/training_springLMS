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

	public Integer savePublisher(Publisher publisher) {

		try {
			// perform write operation depending on which object variables are set
			// update case where both a key and name are given
			if (publisher.getPublisherId() != null && publisher.getPublisherName() != null) {
				pDAO.updatePublisher(publisher);
			}

			// deletion case when an id is given but no name
			else if (publisher.getPublisherId() != null) {
//					blDAO.deleteBookLoansByPubId(publisher.getPublisherId());
//					bcDAO.deleteBookCopiesByPubId(publisher.getPublisherId());
//					bDAO.deleteBooksByPubId(publisher.getPublisherId());
//					pDAO.deletePublisher(publisher);
//					System.out.println("\nPublisher deleted");
			}

			// insertion case otherwise
			else {
				Integer key = pDAO.addPublisher(publisher);
				pDAO.conn.commit();

				System.out.println("Adding " + publisher.getPublisherName() + " to publishers with key " + key);

				return key;
			}

			// commit transaction and display success message
			pDAO.conn.commit();
		} catch (Exception e) {
			// transaction failed. Rollback changes made
			System.out.println("Publisher transaction failed in AdminService");
			try {
				pDAO.conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Could not rollback transaction in AdminService");
			}
			return -1;
		}
		return 0;
	}

	public Integer saveBorrower(Borrower borrower) {

		try {
			// perform write operation depending on which object variables are set
			// update case where both a key and name are given
			if (borrower.getCardNo() != null && borrower.getBorrowerName() != null) {
				borrDAO.updateBorrower(borrower);
			}

			// deletion case when an id is given but no name
			else if (borrower.getCardNo() != null) {
//				blDAO.deleteBookLoansByCardNo(borrower.getCardNo());
//				borrDAO.deleteBorrower(borrower);
//				System.out.println("\nBorrower deleted");
			}

			// insertion case otherwise
			else {
				Integer key = borrDAO.addBorrower(borrower);
				borrDAO.conn.commit();

				System.out.println("Adding " + borrower.getBorrowerName() + " to borrowers with key " + key);

				return key;
			}

			// commit transaction and display success message
			borrDAO.conn.commit();

		} catch (Exception e) {
			// transaction failed. Rollback changes made
			System.out.println("Transaction failed in AdminService");
			try {
				borrDAO.conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Could not rollback transaction in AdminService");
			}
			return -1;
		}
		return 0;
	}

	public Integer saveGenre(Genre genre) {

		try {
			// perform write operation depending on which object variables are set
			// update case where both a key and name are given
			if (genre.getGenreId() != null && genre.getGenreName() != null) {
				gDAO.updateGenre(genre);
			}

			// deletion case when an id is given but no name
			else if (genre.getGenreId() != null) {
//				gDAO.deleteGenre(genre);
			}

			// insertion case otherwise
			else {
				Integer key = gDAO.addGenre(genre);
				gDAO.conn.commit();

				System.out.println("Adding " + genre.getGenreName() + " to genres with key " + key);

				return key;
			}

			// commit transaction and display success message
			gDAO.conn.commit();
		} catch (Exception e) {
			// transaction failed. Rollback changes made
			System.out.println("Transaction failed in AdminService");
			try {
				gDAO.conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Could not rollback transaction in AdminService");
			}
			return -1;
		}
		return 0;
	}

	public Integer saveBranch(LibraryBranch branch) {

		try {

			// perform write operation depending on which object variables are set
			// update case where both a key and name are given
			if (branch.getBranchId() != null && branch.getBranchName() != null) {
				lbDAO.updateLibraryBranch(branch);
				System.out.println("Updated Branch");
			}

			// deletion case when an id is given but no name
			else if (branch.getBranchId() != null) {
//				blDAO.deleteBookLoansByBranchId(branch.getBranchId());
//				bcDAO.deleteBookCopiesByBranchId(branch.getBranchId());
//				brDAO.deleteBranch(branch);
//				System.out.println("\nBranch deleted");
			}

			// insertion case otherwise
			else {
				Integer key = lbDAO.addLibraryBranch(branch);
				lbDAO.conn.commit();

				System.out.println("Adding " + branch.getBranchName() + " to library branches with key " + key);

				return key;
			}

			// commit transaction and display success message
			lbDAO.conn.commit();
		} catch (Exception e) {
			// transaction failed. Rollback changes made
			System.out.println("Transaction failed in AdminService");
			try {
				lbDAO.conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Could not rollback transaction in AdminService");
			}
			return -1;
		}
		return 0;
	}

	public Integer saveAuthor(Author author) {

		try {

			// perform write operation depending on which object variables are set
			// update case where both a key and name are given
			if (author.getAuthorId() != null && author.getAuthorName() != null) {
				aDAO.updateAuthor(author);
			}

			// deletion case when an id is given but no name
			else if (author.getAuthorId() != null) {
//				aDAO.deleteAuthor(author);
			}

			// insertion case otherwise
			else {
				Integer key = aDAO.addAuthor(author);
				aDAO.conn.commit();

				System.out.println("Adding " + author.getAuthorName() + " to authors with key " + key);

				return key;
			}

			// commit transaction and display success message
			aDAO.conn.commit();
		} catch (Exception e) {
			// transaction failed. Rollback changes made
			System.out.println("Transaction failed in AdminService");
			try {
				aDAO.conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Could not rollback transaction in AdminService");
			}
			return -1;
		}
		return 0;
	}

	public void saveBook(Book book) throws SQLException {

		try {

			// perform write operation depending on which object variables are set
			// update case where both a key and name are given
			if (book.getBookId() != null && book.getTitle() != null) {
				bDAO.updateBook(book);
			}

			// deletion case when an id is given but no name
			else if (book.getBookId() != null) {

//				bcDAO.deleteBookCopiesByBookId(book.getBookId());
//
//				bDAO.deleteBook(book);
//				System.out.println("\nBook deleted");
			}

			// insertion case otherwise
			else {
//				bDAO.addBook(book);
//				System.out.println("Adding " + book.getTitle() + " to books");
			}

			// commit transaction and display success message
			bDAO.conn.commit();
		} catch (ClassNotFoundException | SQLException e) {

			// transaction failed. Rollback changes made
			System.out.println("Book transaction failed in AdminService");
			bDAO.conn.rollback();
		}
	}

}
