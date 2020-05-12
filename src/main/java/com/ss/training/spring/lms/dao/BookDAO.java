package com.ss.training.spring.lms.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ss.training.spring.lms.entity.Book;
import com.ss.training.spring.lms.entity.Publisher;

@Repository
public interface BookDAO extends JpaRepository<Book, Long> {

	List<Book> findByTitle(String title);
	
}

//	public Integer addBook(Book book) throws ClassNotFoundException, SQLException{
//		return saveWithPK("INSERT INTO tbl_book (title, pubId) VALUES (?, ?)", new Object[] {book.getTitle(), book.getPublisherId()});
//	}
//	
//	public void addBookAuthorRelationship(Integer bookId, Integer authorId)
//			throws ClassNotFoundException, SQLException {
//		save("INSERT INTO tbl_book_authors (bookId, authorId) VALUES (?, ?)", new Object[] { bookId, authorId });
//	}
//
//	public void updateBook(Book book) throws ClassNotFoundException, SQLException {
//		save("UPDATE tbl_book SET title=?, pubId=? WHERE bookId=?",
//				new Object[] { book.getTitle(), book.getPublisherId(), book.getBookId() });
//	}
//
//	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
//		save("DELETE FROM tbl_book WHERE bookId = ?", new Object[] { book.getBookId() });
//	}
//	





//	public void deleteBooksByAuthorId(Integer authorId) throws ClassNotFoundException, SQLException {
//		save("DELETE FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_authors WHERE authorId=?)",
//				new Object[] { authorId });
//	}
//	




//	public void deleteBooksByPubId(Integer pubId) throws ClassNotFoundException, SQLException {
//		save("DELETE FROM tbl_book WHERE pubId=?", new Object[] { pubId });
//	}
//	
//	public List<Book> readAllBooks() throws ClassNotFoundException, SQLException{
//		return read("SELECT * FROM tbl_book", null);
//	}
//	
//	public List<Book> readBookById(Integer bookId) throws ClassNotFoundException, SQLException {
//		return read("SELECT * FROM tbl_book WHERE bookId=?", new Object[] { bookId });
//	}
//
//	public List<Book> readBookByTitle(String title) throws ClassNotFoundException, SQLException {
//		return read("SELECT * FROM tbl_book WHERE title=?", new Object[] { title });
//	}
//	
//	public List<Book> readAllBooksByAuthor(Integer authorId) throws ClassNotFoundException, SQLException{
//		return read("SELECT * FROM tbl_book where bookId IN (select bookId from tbl_book_authors where authorId= ?)", new Object[]{authorId});
//	}
//	
//	public List<Book> readAllBooksByBranchFromCopy(Integer branchId) throws ClassNotFoundException, SQLException{
//		return read("SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_copies WHERE branchId = ?)", new Object[]{branchId});
//	}
//	
//	public List<Book> readAllBooksByBranchFromLoan(Integer branchId) throws ClassNotFoundException, SQLException{
//		return read("SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_loans WHERE branchId = ?)", new Object[]{branchId});
//	}
//	
//	public List<Book> readAllBooksByCardAndBranch(Integer cardNo,Integer branchId) throws ClassNotFoundException, SQLException{
//		return read("SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_loans WHERE cardNo = ? AND branchId = ? AND dateIn IS NULL)", new Object[]{cardNo, branchId});
//	}
//	
//	public List<Book> readAllBooksByCardAndBranchWithCopy(Integer branchId) throws ClassNotFoundException, SQLException{
//		return read("SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_copies WHERE branchId = ? AND noOfCopies >= 1)", new Object[]{branchId});
//	}
// 
//	@Override
//	public List<Book> extractData(ResultSet rs) throws SQLException {
//		List<Book> books = new ArrayList<>();
//		while(rs.next()){
//			Book book = new Book();
//			book.setBookId(rs.getInt("bookId"));
//			book.setTitle(rs.getString("title"));
//			book.setPublisherId(rs.getInt("pubId"));
//			books.add(book);
//		}
//		return books;
//	}
//
//}
