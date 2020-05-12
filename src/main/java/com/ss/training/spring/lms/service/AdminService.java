package com.ss.training.spring.lms.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transaction;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.training.spring.lms.dao.AuthorDAO;
import com.ss.training.spring.lms.dao.BookCopiesDAO;
import com.ss.training.spring.lms.dao.BookDAO;
import com.ss.training.spring.lms.dao.BorrowerDAO;
import com.ss.training.spring.lms.dao.GenreDAO;
import com.ss.training.spring.lms.dao.LibraryBranchDAO;
import com.ss.training.spring.lms.dao.PublisherDAO;
import com.ss.training.spring.lms.entity.Author;
import com.ss.training.spring.lms.entity.Book;
import com.ss.training.spring.lms.entity.BookCopies;
import com.ss.training.spring.lms.entity.BookCopiesKey;
import com.ss.training.spring.lms.entity.Borrower;
import com.ss.training.spring.lms.entity.Genre;
import com.ss.training.spring.lms.entity.LibraryBranch;
import com.ss.training.spring.lms.entity.Publisher;

@Service
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

	public Integer savePublisher(Publisher publisher) {

		// perform write operation depending on which object variables are set
		// update case where both a key and name are given
		if (publisher.getPublisherId() != null && publisher.getPublisherName() != null) {

			// update publisher if id matches existing record
			if (pDAO.findById(publisher.getPublisherId()).isPresent()) {
				pDAO.save(publisher);
			} else {
				return -1;
			}
		}

		// deletion case when an id is given but no name
		else if (publisher.getPublisherId() != null) {

			// if genre to delete doesn't exist, return error status
			if (pDAO.findById(publisher.getPublisherId()).isPresent()) {
				try {
//					blDAO.deleteBookLoansByPubId(publisher.getPublisherId());
//					bcDAO.deleteBookCopiesByPubId(publisher.getPublisherId());

					pDAO.deleteById(publisher.getPublisherId());
				} catch (Exception e) {
					// query error
					return -1;
				}
			} else {
				// not found
				return 1;
			}
		}

		// insertion case otherwise
		else {
			// insert publisher if name doesn't match existing record
			if (pDAO.findByPublisherName(publisher.getPublisherName()).isEmpty()) {
				try {
					// create the new record
					pDAO.save(publisher);
				} catch (Exception e) {
					// query error
					return -1;
				}
			} else {
				// duplicate entry
				return 1;
			}
		}

		return 0;
	}

	public Integer saveBorrower(Borrower borrower) {

		// perform write operation depending on which object variables are set
		// update case where both a key and name are given
		if (borrower.getCardNo() != null && borrower.getBorrowerName() != null) {

			// update borrower if id matches existing record
			if (borrDAO.findById(borrower.getCardNo()).isPresent()) {
				borrDAO.save(borrower);
			} else {
				return -1;
			}
		}

		// deletion case when an id is given but no name
		else if (borrower.getCardNo() != null) {

			// if genre to delete doesn't exist, return error status
			if (borrDAO.findById(borrower.getCardNo()).isPresent()) {
				try {

//					blDAO.deleteBookLoansByCardNo(borrower.getCardNo());
					borrDAO.deleteById(borrower.getCardNo());
				} catch (Exception e) {
					// query error
					return -1;
				}
			} else {
				// not found
				return 1;
			}
		}

		// insertion case otherwise
		else {
			// insert borrower if name doesn't match existing record
			if (borrDAO.findByBorrowerName(borrower.getBorrowerName()).isEmpty()) {
				try {
					// create the new record
					borrDAO.save(borrower);
				} catch (Exception e) {
					// query error
					return -1;
				}
			} else {
				// duplicate entry
				return 1;
			}
		}
		return 0;
	}

	public Integer saveGenre(Genre genre) {

		// perform write operation depending on which object variables are set
		// update case where both a key and name are given
		if (genre.getGenreId() != null && genre.getGenreName() != null) {

			// update genre if id matches existing record
			if (gDAO.findById(genre.getGenreId()).isPresent()) {
				gDAO.save(genre);
			} else {
				return -1;
			}
		}

		// deletion case when an id is given but no name
		else if (genre.getGenreId() != null) {

			// if genre to delete doesn't exist, return error status
			if (gDAO.findById(genre.getGenreId()).isPresent()) {
				try {
					gDAO.deleteById(genre.getGenreId());
				} catch (Exception e) {
					// query error
					return -1;
				}
			} else {
				// not found
				return 1;
			}
		}

		// insertion case otherwise
		else {
			// insert genre if name doesn't match existing record
			if (gDAO.findByGenreName(genre.getGenreName()).isEmpty()) {
				try {
					// create the new record
					gDAO.save(genre);
				} catch (Exception e) {
					// query error
					return -1;
				}
			} else {
				// duplicate entry
				return 1;
			}
		}
		return 0;
	}

	public Integer saveLibraryBranch(LibraryBranch branch) {

		// perform write operation depending on which object variables are set
		// update case where both a key and name are given
		if (branch.getBranchId() != null && branch.getBranchName() != null) {

			// update branch if id matches existing record
			if (lbDAO.findById(branch.getBranchId()).isPresent()) {
				lbDAO.save(branch);
			} else {
				return -1;
			}
		}

		// deletion case when an id is given but no name
		else if (branch.getBranchId() != null) {

			// if genre to delete doesn't exist, return error status
			if (lbDAO.findById(branch.getBranchId()).isPresent()) {
				try {

					

//					blDAO.deleteBookLoansByBranchId(branch.getBranchId());
					deleteBookCopiesByBranchId(branch.getBranchId());

//					lbDAO.deleteById(branch.getBranchId());
				} catch (Exception e) {
					e.printStackTrace();
					// query error
					return -1;
				}
			} else {
				// not found
				return 1;
			}
		}

		// insertion case otherwise
		else {
			// insert branch if name doesn't match existing record
			if (lbDAO.findByBranchName(branch.getBranchName()).isEmpty()) {
				try {
					// create the new record
					lbDAO.save(branch);
				} catch (Exception e) {
					// query error
					return -1;
				}
			} else {
				// duplicate entry
				return 1;
			}
		}
		return 0;
	}
	
	public Integer deleteBookCopiesByBranchId(Long branchId) 	{
		
		List<BookCopies> result = bcDAO.findByIdBranchId(branchId);

		if (result != null && !result.isEmpty()) {
			for (BookCopies copy : result) {
				bcDAO.deleteById(copy.getId());
			}
			return 0;
		} else {
			return -1;
		}
	}

	public Integer saveAuthor(Author author) {

		// perform write operation depending on which object variables are set
		// update case where both a key and name are given
		if (author.getAuthorId() != null && author.getAuthorName() != null) {

			// update author if id matches existing record
			if (aDAO.findById(author.getAuthorId()).isPresent()) {
				aDAO.save(author);
			} else {
				return -1;
			}
		}

		// deletion case when an id is given but no name
		else if (author.getAuthorId() != null) {

			// if author to delete doesn't exist, return error status
			if (aDAO.findById(author.getAuthorId()).isPresent()) {
				try {

//					blDAO.deleteBookLoansByAuthorId(author.getAuthorId());
//					bcDAO.deleteBookCopiesByAuthorId(author.getAuthorId());

					aDAO.deleteById(author.getAuthorId());
				} catch (Exception e) {
					// query error
					return -1;
				}
			} else {
				// not found
				return 1;
			}
		}

		// insertion case otherwise
		else {
			// update author if id matches existing record
			if (aDAO.findByAuthorName(author.getAuthorName()).isEmpty()) {
				try {
					// create the new record
					aDAO.save(author);
				} catch (Exception e) {
					// query error
					return -1;
				}
			} else {
				// duplicate entry
				return 1;
			}
		}

		return 0;
	}

	public Integer saveBook(Book book) {

		// perform write operation depending on which object variables are set
		// update case where both a key and name are given
		if (book.getBookId() != null && book.getTitle() != null && book.getAuthors() != null
				&& book.getPublisher() != null) {

			// perform update and check for exceptions
			try {
				bDAO.save(book);
			} catch (Exception e) {
				return -1;
			}
		}

		// deletion case when an id is given but no name
		else if (book.getBookId() != null) {

			// if genre to delete doesn't exist, return error status
			if (bDAO.findById(book.getBookId()).isPresent()) {
				try {
//					bcDAO.deleteBookCopiesByBookId(book.getBookId());
//					blDAO.deleteBookLoansByBookId(book.getBookId());
					bDAO.deleteById(book.getBookId());
				} catch (Exception e) {
					// query error
					return -1;
				}
			} else {
				// not found
				return 1;
			}
		}

		// insertion case otherwise
		else {
			// update book if id matches existing record
			if (!bDAO.findByTitle(book.getTitle()).isEmpty()) {
				// duplicate entry
				return 1;
			} else if (book.getAuthors() != null && book.getPublisher() != null) {

				try {
					// create the new record
					bDAO.save(book);
				} catch (Exception e) {
					// query error
					return -1;
				}
			} else {
				// invalid: necessary fields not provided
				return -1;
			}
		}
		return 0;
	}

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
