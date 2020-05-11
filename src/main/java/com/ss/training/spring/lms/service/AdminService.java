package com.ss.training.spring.lms.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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

//	@Autowired
//	BookCopiesDAO bcDAO;
//
//	@Autowired
//	BookLoanDAO blDAO;

	/**
	 * Returns all authors
	 */
	public List<Author> findAllAuthors() {
		return aDAO.findAll();
	}

	/**
	 * Returns author by id
	 */
	public Optional<Author> findByAuthorId(Long authorId) {
		return aDAO.findById(authorId);
	}

	/**
	 * Returns author by name
	 */
	public List<Author> findByAuthorName(String authorName) {
		return aDAO.findByAuthorName(authorName);
	}

	/**
	 * Returns all publishers
	 */
	public List<Publisher> findAllPublishers() {
		return pDAO.findAll();
	}

	/**
	 * Returns publisher by id
	 */
	public Optional<Publisher> findByPublisherId(Long publisherId) {
		return pDAO.findById(publisherId);
	}

	/**
	 * Returns publisher by name
	 */
	public List<Publisher> findByPublisherName(String publisherName) {
		return pDAO.findByPublisherName(publisherName);
	}

	/**
	 * Returns all borrowers
	 */
	public List<Borrower> findAllBorrowers() {
		return borrDAO.findAll();
	}

	/**
	 * Returns borrower by cardNo
	 */
	public Optional<Borrower> findByCardNo(Long cardNo) {
		return borrDAO.findById(cardNo);
	}

	/**
	 * Returns borrower by name
	 */
	public List<Borrower> findByBorrowerName(String borrowerName) {
		return borrDAO.findByBorrowerName(borrowerName);
	}

	/**
	 * Returns all genres
	 */
	public List<Genre> findAllGenres() {
		return gDAO.findAll();
	}

	/**
	 * Returns genre by id
	 */
	public Optional<Genre> findByGenreId(Long genreId) {
		return gDAO.findById(genreId);
	}

	/**
	 * Returns genre by name
	 */
	public List<Genre> findByGenreName(String genreName) {
		return gDAO.findByGenreName(genreName);
	}

	/**
	 * Returns all branches
	 */
	public List<LibraryBranch> findAllBranches() {
		return lbDAO.findAll();
	}

	/**
	 * Returns branch by id
	 */
	public Optional<LibraryBranch> findByBranchId(Long branchId) {
		return lbDAO.findById(branchId);
	}

	/**
	 * Returns branch by name
	 */
	public List<LibraryBranch> findByBranchName(String branchName) {
		return lbDAO.findByBranchName(branchName);
	}

	/**
	 * Returns all books
	 */
	public List<Book> findAllBooks() {
		return bDAO.findAll();
	}

	/**
	 * Returns book by id
	 */
	public Optional<Book> findByBookId(Long bookId) {
		return bDAO.findById(bookId);
	}

	/**
	 * Returns book by title
	 */
	public List<Book> findByBookTitle(String title) {
		return bDAO.findByTitle(title);
	}

//	public Integer savePublisher(Publisher publisher) {
//
//		try {
//			// perform write operation depending on which object variables are set
//			// update case where both a key and name are given
//			if (publisher.getPublisherId() != null && publisher.getPublisherName() != null) {
//				pDAO.updatePublisher(publisher);
//			}
//
//			// deletion case when an id is given but no name
//			else if (publisher.getPublisherId() != null) {
//
//				// if genre to delete doesn't exist, return error status
//				List<Publisher> pResult = pDAO.readPublisherById(publisher.getPublisherId());
//				if (pResult != null && pResult.size() == 0) {
//					return -1;
//				}
//
//				blDAO.deleteBookLoansByPubId(publisher.getPublisherId());
//				blDAO.conn.commit();
//				bcDAO.deleteBookCopiesByPubId(publisher.getPublisherId());
//				bcDAO.conn.commit();
//				bDAO.deleteBooksByPubId(publisher.getPublisherId());
//				bDAO.conn.commit();
//
//				pDAO.deletePublisher(publisher);
//				System.out.println("\nPublisher deleted");
//			}
//
//			// insertion case otherwise
//			else {
//				Integer key = pDAO.addPublisher(publisher);
//				pDAO.conn.commit();
//
//				System.out.println("Adding " + publisher.getPublisherName() + " to publishers with key " + key);
//
//				return key;
//			}
//
//			// commit transaction and display success message
//			pDAO.conn.commit();
//		} catch (Exception e) {
//			// transaction failed. Rollback changes made
//			System.out.println("Publisher transaction failed in AdminService");
//			try {
//				pDAO.conn.rollback();
//			} catch (SQLException e1) {
//				System.out.println("Could not rollback transaction in AdminService");
//			}
//			return -1;
//		}
//		return 0;
//	}
//
//	public Integer saveBorrower(Borrower borrower) {
//
//		try {
//			// perform write operation depending on which object variables are set
//			// update case where both a key and name are given
//			if (borrower.getCardNo() != null && borrower.getBorrowerName() != null) {
//				borrDAO.updateBorrower(borrower);
//			}
//
//			// deletion case when an id is given but no name
//			else if (borrower.getCardNo() != null) {
//
//				// if borrower to delete doesn't exist, return error status
//				List<Borrower> borrResult = borrDAO.readBorrowerById(borrower.getCardNo());
//				if (borrResult != null && borrResult.size() == 0) {
//					return -1;
//				}
//
//				blDAO.deleteBookLoansByCardNo(borrower.getCardNo());
//				blDAO.conn.commit();
//
//				borrDAO.deleteBorrower(borrower);
//			}
//
//			// insertion case otherwise
//			else {
//				Integer key = borrDAO.addBorrower(borrower);
//				borrDAO.conn.commit();
//
//				System.out.println("Adding " + borrower.getBorrowerName() + " to borrowers with key " + key);
//
//				return key;
//			}
//
//			// commit transaction and display success message
//			borrDAO.conn.commit();
//
//		} catch (Exception e) {
//			// transaction failed. Rollback changes made
//			System.out.println("Transaction failed in AdminService");
//			try {
//				borrDAO.conn.rollback();
//			} catch (SQLException e1) {
//				System.out.println("Could not rollback transaction in AdminService");
//			}
//			return -1;
//		}
//		return 0;
//	}
//
//	public Integer saveGenre(Genre genre) {
//
//		try {
//			// perform write operation depending on which object variables are set
//			// update case where both a key and name are given
//			if (genre.getGenreId() != null && genre.getGenreName() != null) {
//				gDAO.updateGenre(genre);
//			}
//
//			// deletion case when an id is given but no name
//			else if (genre.getGenreId() != null) {
//
//				// if genre to delete doesn't exist, return error status
//				List<Genre> gResult = gDAO.readGenresById(genre.getGenreId());
//				if (gResult != null && gResult.size() == 0) {
//					return -1;
//				}
//
//				gDAO.deleteGenre(genre);
//
//			}
//
//			// insertion case otherwise
//			else {
//				Integer key = gDAO.addGenre(genre);
//				gDAO.conn.commit();
//
//				System.out.println("Adding " + genre.getGenreName() + " to genres with key " + key);
//
//				return key;
//			}
//
//			// commit transaction and display success message
//			gDAO.conn.commit();
//		} catch (Exception e) {
//			// transaction failed. Rollback changes made
//			System.out.println("Transaction failed in AdminService");
//			try {
//				gDAO.conn.rollback();
//			} catch (SQLException e1) {
//				System.out.println("Could not rollback transaction in AdminService");
//			}
//			return -1;
//		}
//		return 0;
//	}
//
//	public Integer saveBranch(LibraryBranch branch) {
//
//		try {
//
//			// perform write operation depending on which object variables are set
//			// update case where both a key and name are given
//			if (branch.getBranchId() != null && branch.getBranchName() != null) {
//				lbDAO.updateLibraryBranch(branch);
//				System.out.println("Updated Branch");
//			}
//
//			// deletion case when an id is given but no name
//			else if (branch.getBranchId() != null) {
//
//				// if branch to delete doesn't exist, return error status
//				List<LibraryBranch> lbResult = lbDAO.readLibraryBranchById(branch.getBranchId());
//				if (lbResult != null && lbResult.size() == 0) {
//					return -1;
//				}
//
//				blDAO.deleteBookLoansByBranchId(branch.getBranchId());
//				blDAO.conn.commit();
//				bcDAO.deleteBookCopiesByBranchId(branch.getBranchId());
//				bcDAO.conn.commit();
//
//				lbDAO.deleteLibraryBranch(branch);
//			}
//
//			// insertion case otherwise
//			else {
//				Integer key = lbDAO.addLibraryBranch(branch);
//				lbDAO.conn.commit();
//
//				System.out.println("Adding " + branch.getBranchName() + " to library branches with key " + key);
//
//				return key;
//			}
//
//			// commit transaction and display success message
//			lbDAO.conn.commit();
//		} catch (Exception e) {
//			// transaction failed. Rollback changes made
//			System.out.println("Transaction failed in AdminService");
//			try {
//				lbDAO.conn.rollback();
//			} catch (SQLException e1) {
//				System.out.println("Could not rollback transaction in AdminService");
//			}
//			return -1;
//		}
//		return 0;
//	}

	public Integer saveAuthor(Author author) {

		Author returnAuthor = null;
		
		// perform write operation depending on which object variables are set
		// update case where both a key and name are given
		if (author.getAuthorId() != null && author.getAuthorName() != null) {
			 returnAuthor = aDAO.save(author);
			 if (returnAuthor == null) return -1; 
		}

//			// deletion case when an id is given but no name
//			else if (author.getAuthorId() != null) {
//
//				// if author to delete doesn't exist, return error status
//				List<Author> aResult = aDAO.readAuthorById(author.getAuthorId());
//				if (aResult != null && aResult.size() == 0) {
//					return -1;
//				}
//
//				// delete entries from all tables where authorId to be deleted is referred to
//				blDAO.deleteBookLoansByAuthorId(author.getAuthorId());
//				blDAO.conn.commit();
//				bcDAO.deleteBookCopiesByAuthorId(author.getAuthorId());
//				bcDAO.conn.commit();
//				bDAO.deleteBooksByAuthorId(author.getAuthorId());
//				bDAO.conn.commit();
//
//				aDAO.deleteAuthor(author);
//			}
//
//			// insertion case otherwise
//			else {
//				Integer key = aDAO.addAuthor(author);
//				aDAO.conn.commit();
//
//				System.out.println("Adding " + author.getAuthorName() + " to authors with key " + key);
//
//				return key;
//			}
//
//			// commit transaction and display success message
//			aDAO.conn.commit();
//		} catch (Exception e) {
//			// transaction failed. Rollback changes made
//			System.out.println("Transaction failed in AdminService");
//			try {
//				aDAO.conn.rollback();
//			} catch (SQLException e1) {
//				System.out.println("Could not rollback transaction in AdminService");
//			}
//			return -1;
//		}
		return 0;
	}

//	public Integer saveBook(Book book) {
//
//		try {
//
//			// perform write operation depending on which object variables are set
//			// update case where both a key and name are given
//			if (book.getBookId() != null && book.getTitle() != null) {
//				bDAO.updateBook(book);
//			}
//
//			// deletion case when an id is given but no name
//			else if (book.getBookId() != null) {
//
//				// if book to delete doesn't exist, return error status
//				List<Book> bResult = bDAO.readBookById(book.getBookId());
//				if (bResult != null && bResult.size() == 0) {
//					return -1;
//				}
//
//				bcDAO.deleteBookCopiesByBookId(book.getBookId());
//				bcDAO.conn.commit();
//
//				bDAO.deleteBook(book);
//				System.out.println("\nBook deleted");
//			}
//
//			// insertion case otherwise
//			else {
//				List<Author> aResult = null;
//				List<Genre> gResult = null;
//				Integer authorKey = -1;
//				Integer key = bDAO.addBook(book);
//
//				// add authors
//				if (book.getAuthors() != null && book.getAuthors().size() > 0) {
//					for (Author a : book.getAuthors()) {
//						if (a.getAuthorId() != null && a.getAuthorName() == null) {
//							aResult = aDAO.readAuthorById(a.getAuthorId());
//							if (aResult != null && aResult.size() > 0) {
//								bDAO.addBookAuthorRelationship(key, a.getAuthorId());
//							}
//						} else if (a.getAuthorId() == null && a.getAuthorName() != null) {
//							aResult = aDAO.readAuthorByName(a.getAuthorName());
//							if (aResult != null && aResult.size() > 0) {
//								bDAO.addBookAuthorRelationship(key, aResult.get(0).getAuthorId());
//							} else {
//								authorKey = this.addAuthor(a);
//								if (authorKey != null & authorKey > 0) {
//									bDAO.addBookAuthorRelationship(key, authorKey);
//								}
//							}
//						}
//					}
//				}
//
//				bDAO.conn.commit();
//
//				// add genres
//				if (book.getGenres() != null && book.getGenres().size() > 0) {
//					for (Genre g : book.getGenres()) {
//						if (g.getGenreId() != null && g.getGenreName() == null) {
//							gResult = gDAO.readGenresById(g.getGenreId());
//							if (gResult != null && gResult.size() > 0) {
//								insertBookGenreRelationship(g.getGenreId(), key);
//							}
//						}
//					}
//				}
//
//				System.out.println("Adding " + book.getTitle() + " to books with key " + key);
//
//				return key;
//			}
//
//			// commit transaction and display success message
//			bDAO.conn.commit();
//		} catch (Exception e) {
//			// transaction failed. Rollback changes made
//			System.out.println("Transaction failed in AdminService");
//			try {
//				bDAO.conn.rollback();
//			} catch (SQLException e1) {
//				System.out.println("Could not rollback transaction in AdminService");
//			}
//			return -1;
//		}
//		return 0;
//	}
//
//	public Integer addAuthor(Author a) {
//		Integer key = -1;
//		try {
//			key = aDAO.addAuthor(a);
//			aDAO.conn.commit();
//		} catch (Exception e) {
//			System.out.println("Add author failed");
//			try {
//				aDAO.conn.rollback();
//			} catch (SQLException e1) {
//				System.out.println("Could not rollback author query");
//			}
//		}
//		return key;
//	}
//
//	public void insertBookGenreRelationship(Integer genreId, Integer bookId) throws SQLException {
//		try {
//			gDAO.addBookGenreRelationship(genreId, bookId);
//
//			// commit transaction and display success message
//			gDAO.conn.commit();
//		} catch (ClassNotFoundException | SQLException e) {
//
//			// transaction failed. Rollback changes made
//			System.out.println("Book/Genre insertion failed in AdminService");
//			gDAO.conn.rollback();
//		}
//	}
//
//	public Integer extendBookLoan(BookLoan bookLoan) {
//
//		boolean found = false;
//		LocalDateTime dateTimeDueDate = null;
//		LocalDateTime dateTimeDueDateExtended = null;
//		String strDueDateExtended = null;
//
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//		LocalDateTime dateTimeNow = LocalDateTime.now();
//		String strDateTimeNow = dateTimeNow.format(formatter);
//
//		try {
//			// if book to delete doesn't exist, return error status
//			List<BookLoan> blResult = blDAO.readBookLoansDue(strDateTimeNow);
//
//			if (blResult != null && blResult.size() > 0) {
//				for (BookLoan bl : blResult) {
//					if (bl.getBookId() == bookLoan.getBookId() && bl.getCardNo() == bookLoan.getCardNo()
//							&& bl.getDueDate().equals(bookLoan.getDueDate())) {
//						found = true;
//						bookLoan = bl;
//						System.out.println("Matching overdue book found");
//						break;
//					}
//				}
//				if (found == false) {
//					return -1;
//				}
//			}
//
//			dateTimeDueDate = LocalDateTime.parse(bookLoan.getDueDate(), formatter);
//			dateTimeDueDateExtended = dateTimeDueDate.plusDays(7);
//			strDueDateExtended = dateTimeDueDateExtended.format(formatter);
//
//			updateDueDate(bookLoan, strDueDateExtended);
//		} catch (Exception e) {
//			System.out.println("updateDueDate failed");
//			return -1;
//		}
//
//		System.out.println("\nThe new extended due date is: " + strDueDateExtended);
//		return 0;
//	}
//
//	public void updateDueDate(BookLoan loan, String newDate) throws SQLException {
//
//		try {
//
//			blDAO.updateBookLoan(loan.getBookId(), loan.getBranchId(), loan.getCardNo(), loan.getDateOut(), newDate);
//
//			// commit transaction and display success message
//			blDAO.conn.commit();
//
//		} catch (ClassNotFoundException | SQLException e) {
//
//			// transaction failed. Rollback changes made
//			System.out.println("Due date update failed in AdminService");
//			blDAO.conn.rollback();
//		}
//
//	}

}
