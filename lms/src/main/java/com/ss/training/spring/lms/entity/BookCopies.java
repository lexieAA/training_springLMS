package com.ss.training.spring.lms.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_book_copies")
public class BookCopies implements Serializable {

	private static final long serialVersionUID = 8084402665858290667L;

	@EmbeddedId
	BookCopiesKey id;

	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "bookId")
	private Book book;

	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "branchId")
	private LibraryBranch branch;

	@Column(name = "noOfCopies")
	private Integer noOfCopies;

	public BookCopiesKey getId() {
		return id;
	}

	public void setId(BookCopiesKey id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LibraryBranch getBranch() {
		return branch;
	}

	public void setBranch(LibraryBranch branch) {
		this.branch = branch;
	}

	public Integer getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BookCopies)) {
			return false;
		}
		BookCopies other = (BookCopies) obj;
		return Objects.equals(id, other.id);
	}

}