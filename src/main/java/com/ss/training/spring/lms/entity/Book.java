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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tbl_book")
public class Book implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 697612263062099931L;

	@Id
	@Column(name = "bookId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;

	@Column(name = "title")
	private String title;

	@ManyToMany
	@JoinTable(name = "tbl_book_authors", 
		joinColumns = @JoinColumn(name = "bookId"), 
		inverseJoinColumns = @JoinColumn(name = "authorId"))
//	@JsonBackReference
	private List<Author> authors;

	@ManyToMany
	@JoinTable(name = "tbl_book_genres", 
		joinColumns = @JoinColumn(name = "bookId"), 
		inverseJoinColumns = @JoinColumn(name = "genre_id"))
//	@JsonBackReference
	private List<Genre> genres;

	@ManyToOne
	@JoinColumn(name = "pubId", nullable = false)
	private Publisher publisher;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
	@JsonBackReference(value="bookCopies")
	private List<BookCopies> bookCopies;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
	@JsonBackReference(value="booksLoans")
	private List<BookLoan> bookLoans;

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

	public List<BookCopies> getBookCopies() {
		return bookCopies;
	}

	public void setBookCopies(List<BookCopies> bookCopies) {
		this.bookCopies = bookCopies;
	}

	public List<BookLoan> getBookLoans() {
		return bookLoans;
	}

	public void setBookLoans(List<BookLoan> bookLoans) {
		this.bookLoans = bookLoans;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + ((bookCopies == null) ? 0 : bookCopies.hashCode());
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((bookLoans == null) ? 0 : bookLoans.hashCode());
		result = prime * result + ((genres == null) ? 0 : genres.hashCode());
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (bookCopies == null) {
			if (other.bookCopies != null)
				return false;
		} else if (!bookCopies.equals(other.bookCopies))
			return false;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (bookLoans == null) {
			if (other.bookLoans != null)
				return false;
		} else if (!bookLoans.equals(other.bookLoans))
			return false;
		if (genres == null) {
			if (other.genres != null)
				return false;
		} else if (!genres.equals(other.genres))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	



}