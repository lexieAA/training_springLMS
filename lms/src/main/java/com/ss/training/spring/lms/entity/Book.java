package com.ss.training.spring.lms.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_book")
public class Book implements Serializable {

	private static final long serialVersionUID = 3659016787776471054L;

	@Id
	@Column(name = "bookId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;

	@Column(name = "title")
	private String title;

	@ManyToMany
	@JoinTable(name = "tbl_book_authors", joinColumns = @JoinColumn(name = "bookId"), inverseJoinColumns = @JoinColumn(name = "authorId"))
	private List<Author> authors;

	@ManyToMany
	@JoinTable(name = "tbl_book_genres", joinColumns = @JoinColumn(name = "bookId"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private List<Genre> genres;

	@ManyToOne
	@JoinColumn(name = "pubId", nullable = false)
	private Publisher publisher;

	@OneToMany(mappedBy = "bookId")
	private List<BookLoan> bookLoans;

	@OneToMany(mappedBy = "bookId")
	private List<BookCopies> bookCopies;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public List<BookLoan> getBookLoans() {
		return bookLoans;
	}

	public void setBookLoans(List<BookLoan> bookLoans) {
		this.bookLoans = bookLoans;
	}

	public List<BookCopies> getBookCopies() {
		return bookCopies;
	}

	public void setBookCopies(List<BookCopies> bookCopies) {
		this.bookCopies = bookCopies;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authors, bookCopies, bookId, bookLoans, genres, publisher, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Book)) {
			return false;
		}
		Book other = (Book) obj;
		return Objects.equals(authors, other.authors) && Objects.equals(bookCopies, other.bookCopies)
				&& Objects.equals(bookId, other.bookId) && Objects.equals(bookLoans, other.bookLoans)
				&& Objects.equals(genres, other.genres) && Objects.equals(publisher, other.publisher)
				&& Objects.equals(title, other.title);
	}

}