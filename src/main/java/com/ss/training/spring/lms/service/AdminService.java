package com.ss.training.spring.lms.service;

import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;

import com.ss.training.spring.lms.dao.AuthorDAO;
import com.ss.training.spring.lms.dao.BookCopiesDAO;
import com.ss.training.spring.lms.dao.BookDAO;
import com.ss.training.spring.lms.dao.BookLoanDAO;
import com.ss.training.spring.lms.dao.BorrowerDAO;
import com.ss.training.spring.lms.dao.GenreDAO;
import com.ss.training.spring.lms.dao.LibraryBranchDAO;
import com.ss.training.spring.lms.dao.PublisherDAO;
import com.ss.training.spring.lms.entity.Author;
import com.ss.training.spring.lms.entity.Book;
import com.ss.training.spring.lms.entity.BookCopies;
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

	@Autowired
	BookCopiesDAO bcDAO;

	@Autowired
	BookLoanDAO blDAO;

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
				for (Book b : books) {
					List<Author> listA = aDAO.readAllAuthorsByBookId(b.getBookId());
					b.setAuthors(listA);
					b.setGenres(gDAO.readGenresByBookId(b.getBookId()));
				}
				return books;
			}

			// if a name is provided, perform a search by name
			else if (bookTitle != null) {
				List<Book> books = bDAO.readBookByTitle(bookTitle);
				for (Book b : books) {
					b.setAuthors(aDAO.readAllAuthorsByBookId(b.getBookId()));
					b.setGenres(gDAO.readGenresByBookId(b.getBookId()));
				}
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

				// if genre to delete doesn't exist, return error status
				List<Publisher> pResult = pDAO.readPublisherById(publisher.getPublisherId());
				if (pResult != null && pResult.size() == 0) {
					return -1;
				}

				blDAO.deleteBookLoansByPubId(publisher.getPublisherId());
				blDAO.conn.commit();
				bcDAO.deleteBookCopiesByPubId(publisher.getPublisherId());
				bcDAO.conn.commit();
				bDAO.deleteBooksByPubId(publisher.getPublisherId());
				bDAO.conn.commit();

				pDAO.deletePublisher(publisher);
				System.out.println("\nPublisher deleted");
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
				
				// if borrower to delete doesn't exist, return error status
				List<Borrower> borrResult = borrDAO.readBorrowerById(borrower.getCardNo());
				if (borrResult != null && borrResult.size() == 0) {
					return -1;
				}
				
				blDAO.deleteBookLoansByCardNo(borrower.getCardNo());
				blDAO.conn.commit();
				
				borrDAO.deleteBorrower(borrower);
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

				// if genre to delete doesn't exist, return error status
				List<Genre> gResult = gDAO.readGenresById(genre.getGenreId());
				if (gResult != null && gResult.size() == 0) {
					return -1;
				}

				gDAO.deleteGenre(genre);

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
				
				// if branch to delete doesn't exist, return error status
				List<LibraryBranch> lbResult = lbDAO.readLibraryBranchById(branch.getBranchId());
				if (lbResult != null && lbResult.size() == 0) {
					return -1;
				}
				
				blDAO.deleteBookLoansByBranchId(branch.getBranchId());
				blDAO.conn.commit();
				bcDAO.deleteBookCopiesByBranchId(branch.getBranchId());
				bcDAO.conn.commit();
				
				lbDAO.deleteLibraryBranch(branch);
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

				// if author to delete doesn't exist, return error status
				List<Author> aResult = aDAO.readAuthorById(author.getAuthorId());
				if (aResult != null && aResult.size() == 0) {
					return -1;
				}

				// delete entries from all tables where authorId to be deleted is referred to
				blDAO.deleteBookLoansByAuthorId(author.getAuthorId());
				blDAO.conn.commit();
				bcDAO.deleteBookCopiesByAuthorId(author.getAuthorId());
				bcDAO.conn.commit();
				bDAO.deleteBooksByAuthorId(author.getAuthorId());
				bDAO.conn.commit();

				aDAO.deleteAuthor(author);
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

	public Integer saveBook(Book book) {

		try {

			// perform write operation depending on which object variables are set
			// update case where both a key and name are given
			if (book.getBookId() != null && book.getTitle() != null) {
				bDAO.updateBook(book);
			}

			// deletion case when an id is given but no name
			else if (book.getBookId() != null) {

				// if book to delete doesn't exist, return error status
				List<Book> bResult = bDAO.readBookById(book.getBookId());
				if (bResult != null && bResult.size() == 0) {
					return -1;
				}

				bcDAO.deleteBookCopiesByBookId(book.getBookId());
				bcDAO.conn.commit();

				bDAO.deleteBook(book);
				System.out.println("\nBook deleted");
			}

			// insertion case otherwise
			else {
				List<Author> aResult = null;
				List<Genre> gResult = null;
				Integer authorKey = -1;
				Integer key = bDAO.addBook(book);

				// add authors
				if (book.getAuthors() != null && book.getAuthors().size() > 0) {
					for (Author a : book.getAuthors()) {
						if (a.getAuthorId() != null && a.getAuthorName() == null) {
							aResult = aDAO.readAuthorById(a.getAuthorId());
							if (aResult != null && aResult.size() > 0) {
								bDAO.addBookAuthorRelationship(key, a.getAuthorId());
							}
						} else if (a.getAuthorId() == null && a.getAuthorName() != null) {
							aResult = aDAO.readAuthorByName(a.getAuthorName());
							if (aResult != null && aResult.size() > 0) {
								bDAO.addBookAuthorRelationship(key, aResult.get(0).getAuthorId());
							} else {
								authorKey = this.addAuthor(a);
								if (authorKey != null & authorKey > 0) {
									bDAO.addBookAuthorRelationship(key, authorKey);
								}
							}
						}
					}
				}

				bDAO.conn.commit();

				// add genres
				if (book.getGenres() != null && book.getGenres().size() > 0) {
					for (Genre g : book.getGenres()) {
						if (g.getGenreId() != null && g.getGenreName() == null) {
							gResult = gDAO.readGenresById(g.getGenreId());
							if (gResult != null && gResult.size() > 0) {
								insertBookGenreRelationship(g.getGenreId(), key);
							}
						}
					}
				}

				System.out.println("Adding " + book.getTitle() + " to books with key " + key);

				return key;
			}

			// commit transaction and display success message
			bDAO.conn.commit();
		} catch (Exception e) {
			// transaction failed. Rollback changes made
			System.out.println("Transaction failed in AdminService");
			try {
				bDAO.conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Could not rollback transaction in AdminService");
			}
			return -1;
		}
		return 0;
	}

	public Integer addAuthor(Author a) {
		Integer key = -1;
		try {
			key = aDAO.addAuthor(a);
			aDAO.conn.commit();
		} catch (Exception e) {
			System.out.println("Add author failed");
			try {
				aDAO.conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Could not rollback author query");
			}
		}
		return key;
	}

	public void insertBookGenreRelationship(Integer genreId, Integer bookId) throws SQLException {
		try {
			gDAO.addBookGenreRelationship(genreId, bookId);

			// commit transaction and display success message
			gDAO.conn.commit();
		} catch (ClassNotFoundException | SQLException e) {

			// transaction failed. Rollback changes made
			System.out.println("Book/Genre insertion failed in AdminService");
			gDAO.conn.rollback();
		}
	}

}
