package com.ss.training.spring.lms.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ss.training.spring.lms.entity.Author;

@Repository
public interface AuthorDAO extends JpaRepository<Author, Long> {

	List<Author> findByAuthorName(String authorName);
//	void deleteBooksByAuthorId(Long authorId);
	
}

//	public AuthorDAO(Connection conn) {
//		super(conn);
//	}
//
//	public Integer addAuthor(Author author) throws ClassNotFoundException, SQLException {
//		return saveWithPK("INSERT INTO tbl_author (authorName) VALUES (?)", new Object[] { author.getAuthorName() });
//	}
//
//	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException {
//		save("UPDATE tbl_author SET authorName = ? WHERE authorId = ?",
//				new Object[] { author.getAuthorName(), author.getAuthorId() });
//	}
//
//	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException {
//		save("DELETE FROM tbl_author WHERE authorId = ?", new Object[] { author.getAuthorId() });
//	}
//
//	public List<Author> readAllAuthors() throws ClassNotFoundException, SQLException {
//		return read("SELECT * FROM tbl_author", null);
//	}
//
//	public List<Author> readAuthorById(Integer authorId) throws ClassNotFoundException, SQLException {
//		return read("SELECT * FROM tbl_author WHERE authorId=?", new Object[] { authorId });
//	}
//
//	public List<Author> readAllAuthorsByBookId(Integer bookId) throws ClassNotFoundException, SQLException {
//		return read(
//				"SELECT * FROM tbl_author WHERE authorId IN (SELECT authorId FROM tbl_book_authors WHERE bookId=?)",
//				new Object[] { bookId });
//	}
