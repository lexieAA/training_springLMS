//package com.ss.training.spring.lms.dao;
//
//import org.springframework.stereotype.Repository;
//import java.util.List;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//@Repository
//public interface BookLoanDAO extends JpaRepository<BookLoan, Long> {

//	List<Book> findByTitle(String title);

//}
//
//public class BookLoanDAO extends BaseDAO<BookLoan> {
//
//	public BookLoanDAO(Connection conn) {
//		super(conn);
//	}
//
//	public void addBookLoan(BookLoan loan) throws ClassNotFoundException, SQLException {
//		save("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) VALUES (?,?,?,CURRENT_TIMESTAMP, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 7 DAY),NULL)",
//				new Object[] { loan.getBookId(), loan.getBranchId(), loan.getCardNo() });
//	}
//
//	public void updateBookLoan(BookLoan loan) throws ClassNotFoundException, SQLException {
//		save("UPDATE tbl_book_loans SET dateIn = CURRENT_TIMESTAMP WHERE bookId = ? AND branchId = ? AND cardNo = ? AND dateOut =?",
//				new Object[] { loan.getBookId(), loan.getBranchId(), loan.getCardNo(), loan.getDateOut() });
//	}
//
//	public void updateBookLoanDate(BookLoan loan) throws ClassNotFoundException, SQLException {
//		save("UPDATE tbl_book_loans SET dateOut = CURRENT_TIMESTAMP, dueDate = DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 7 DAY) WHERE bookId = ? AND branchId = ? AND cardNo = ? AND dateOut =?",
//				new Object[] { loan.getBookId(), loan.getBranchId(), loan.getCardNo(), loan.getDateOut() });
//	}
//
//	public void updateBookLoan(Integer bookId, Integer branchId, Integer cardNo, String dateIn)
//			throws SQLException, ClassNotFoundException {
//
//		// create a statement Object
//		save("UPDATE tbl_book_loans SET dateIn=? WHERE bookId=? AND branchId=? AND cardNo=? AND dateIn IS NULL",
//				new Object[] { dateIn, bookId, branchId, cardNo });
//	}
//
//	public void updateBookLoan(Integer bookId, Integer branchId, Integer cardNo, String dateOut, String dueDate)
//			throws SQLException, ClassNotFoundException {
//
//		// create a statement Object
//		save("UPDATE tbl_book_loans SET dueDate=? WHERE bookId=? AND branchId=? AND cardNo=? AND dateOut=?",
//				new Object[] { dueDate, bookId, branchId, cardNo, dateOut });
//	}
//
//	public void deleteBookLoan(BookLoan loan) throws ClassNotFoundException, SQLException {
//		save("DELETE FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo = ? AND dateOut =?",
//				new Object[] { loan.getBookId(), loan.getBranchId(), loan.getCardNo(), loan.getDateOut() });
//	}
//
//	public void deleteBookLoansByAuthorId(Integer authorId) throws ClassNotFoundException, SQLException {
//		save("DELETE FROM tbl_book_loans WHERE bookId IN (SELECT bookId FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_authors WHERE authorId=?))",
//				new Object[] { authorId });
//	}
//
//	public void deleteBookLoansByPubId(Integer publisherId) throws ClassNotFoundException, SQLException {
//		save("DELETE FROM tbl_book_loans WHERE bookId IN (SELECT bookId FROM tbl_book WHERE pubId=?)",
//				new Object[] { publisherId });
//	}
//
//	public void deleteBookLoansByBranchId(Integer branchId) throws ClassNotFoundException, SQLException {
//		save("DELETE FROM tbl_book_loans WHERE branchId=?", new Object[] { branchId });
//	}
//
//	public void deleteBookLoansByCardNo(Integer cardNo) throws ClassNotFoundException, SQLException {
//		save("DELETE FROM tbl_book_loans WHERE cardNo=?", new Object[] { cardNo });
//	}
//
//	public List<BookLoan> readAllBookLoans() throws ClassNotFoundException, SQLException {
//		return read("SELECT * FROM tbl_book_loans", null);
//	}
//
//	public List<BookLoan> readAllBookLoansByKeys(BookLoan loan) throws ClassNotFoundException, SQLException {
//		return read("SELECT * FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo = ? AND dateOut =?",
//				new Object[] { loan.getBookId(), loan.getBranchId(), loan.getCardNo(), loan.getDateOut() });
//	}
//
//	public List<BookLoan> readBookLoanByBookBranchCardNo(BookLoan loan) throws ClassNotFoundException, SQLException {
//		return read("SELECT * FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo = ?",
//				new Object[] { loan.getBookId(), loan.getBranchId(), loan.getCardNo() });
//	}
//	
//	public List<BookLoan> readBookLoansDue(String currentDateTime) throws ClassNotFoundException, SQLException {
//		return read("SELECT * FROM tbl_book_Loans WHERE dueDate < ? AND dateIn IS NULL",
//				new Object[] { currentDateTime });
//
//	}
//
//	@Override
//	public List<BookLoan> extractData(ResultSet rs) throws SQLException {
//		List<BookLoan> loans = new ArrayList<>();
//		while (rs.next()) {
//			BookLoan loan = new BookLoan();
//			loan.setBookId(rs.getInt("bookId"));
//			loan.setBranchId(rs.getInt("branchId"));
//			loan.setCardNo(rs.getInt("cardNo"));
//			loan.setDateOut(rs.getString("dateOut"));
//			loan.setDueDate(rs.getString("dueDate"));
//			loan.setDateIn(rs.getString("dateIn"));
//			loans.add(loan);
//		}
//		return loans;
//	}
//
//}
