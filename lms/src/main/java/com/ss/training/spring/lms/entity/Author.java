package com.ss.training.spring.lms.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_author")
public class Author implements Serializable {
	
	private static final long serialVersionUID = 8115386083228601769L;

	@Id
	@Column(name = "authorId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long authorId;

	@Column(name = "authorName")
	private String authorName;

	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tbl_book_authors", 
      joinColumns = @JoinColumn(name = "bookId"), 
      inverseJoinColumns = @JoinColumn(name = "authorId", 
      referencedColumnName = "authorId"))
	private List<Book> books;

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorId, authorName, books);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Author)) {
			return false;
		}
		Author other = (Author) obj;
		return Objects.equals(authorId, other.authorId) && Objects.equals(authorName, other.authorName)
				&& Objects.equals(books, other.books);
	}

}