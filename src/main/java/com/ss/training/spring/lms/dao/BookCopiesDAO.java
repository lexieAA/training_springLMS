//package com.ss.training.spring.lms.dao;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.ss.training.spring.lms.entity.BookCopies;
//
//public class BookCopiesDAO extends BaseDAO<BookCopies>{
//
//	public BookCopiesDAO(Connection conn) {
//		super(conn);
//	}
//
//	public void addBookCopies(BookCopies copy) throws ClassNotFoundException, SQLException{
//		save("INSERT INTO tbl_book_copies (bookId, branchId, noOfCopies) VALUES (?, ?, ?)", 
//				new Object[] {copy.getBookId(), copy.getBranchId(), copy.getNoOfCopies()});
//	}
//
//	public void updateBookCopies(BookCopies copy)  throws ClassNotFoundException, SQLException{
//		save("UPDATE tbl_book_copies SET noOfCopies = ?  WHERE bookId = ? AND branchId = ?", 
//				new Object[] {copy.getNoOfCopies(), copy.getBookId(),copy.getBranchId()});
//	}
//
//	public void deleteBookCopies(BookCopies copy)  throws ClassNotFoundException, SQLException{
//		save("DELETE FROM tbl_book_copies WHERE bookId = ? AND branchId = ?", 
//				new Object[]{copy.getBookId(),copy.getBranchId()});
//	}
//	
//	public void deleteBookCopiesByBranchId(Integer branchId) throws ClassNotFoundException, SQLException {
//		save("DELETE FROM tbl_book_copies WHERE branchId=?",
//				new Object[] { branchId });
//	}
//	
//	/**
//	 * Deletes all book copies in the book copies table where the author matches the
//	 * passed bookId parameter
//	 * 
//	 * @param bookId deletes all book copies matching this bookId
//	 * @throws ClassNotFoundException
//	 * @throws SQLException
//	 */
//	public void deleteBookCopiesByAuthorId(Integer authorId) throws ClassNotFoundException, SQLException {
//		save("DELETE FROM tbl_book_copies WHERE bookId IN (SELECT bookId FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_authors WHERE authorId=?))",
//				new Object[] { authorId });
//	}
//	
//	/**
//	 * Deletes all book copies in the book copies table where the author matches the
//	 * passed bookId parameter
//	 * 
//	 * @param bookId deletes all book copies matching this bookId
//	 * @throws ClassNotFoundException
//	 * @throws SQLException
//	 */
//	public void deleteBookCopiesByBookId(Integer bookId) throws ClassNotFoundException, SQLException {
//		save("DELETE FROM tbl_book_copies WHERE bookId IN (SELECT bookId FROM tbl_book WHERE bookId=?)",
//				new Object[] { bookId });
//	}
//	
//	public void deleteBookCopiesByPubId(Integer publisherId) throws ClassNotFoundException, SQLException {
//		save("DELETE FROM tbl_book_copies WHERE bookId IN (SELECT bookId FROM tbl_book WHERE pubId=?)",
//				new Object[] { publisherId });
//	}
//	
//	public List<BookCopies> readAllBookCopies() throws ClassNotFoundException, SQLException{
//		return read("SELECT * FROM tbl_book_copies", null);
//	}
//	
//	public List<BookCopies> readBookCopy(BookCopies copy) throws ClassNotFoundException, SQLException{
//		return read("SELECT * FROM tbl_book_copies WHERE bookId = ? AND branchId = ?",new Object[]{copy.getBookId(),copy.getBranchId()});
//	}
//	
//	public List<BookCopies> readAllBookCopiesByBranch(Integer branchId) throws ClassNotFoundException, SQLException{
//		return read("SELECT * FROM tbl_book_copies WHERE branchId = ?", new Object[] {branchId});
//	}
//
//	@Override
//	public List<BookCopies> extractData(ResultSet rs) throws SQLException {
//		List<BookCopies> copies = new ArrayList<>();
//		while(rs.next()){
//			BookCopies copy= new BookCopies();
//			copy.setBookId(rs.getInt("bookId"));
//			copy.setBranchId(rs.getInt("branchId"));
//			copy.setNoOfCopies(rs.getInt("noOfCopies"));
//			copies.add(copy);
//		}
//		return copies;
//	}
//
//}
