package com.ss.training.spring.lms.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_book_copies")
public class BookCopies implements Serializable {

	private static final long serialVersionUID = 8084402665858290667L;

	@EmbeddedId
	BookCopiesKey id;

	@Column(name = "noOfCopies")
	private Integer noOfCopies;
	
	public BookCopies() {
		
	}

	public BookCopies(BookCopiesKey id, Integer noOfCopies) {
		super();
		this.id = id;
		this.noOfCopies = noOfCopies;
	}


	/**
	 * @return the id
	 */
	public BookCopiesKey getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(BookCopiesKey id) {
		this.id = id;
	}


	/**
	 * @return the noOfCopies
	 */
	public Integer getNoOfCopies() {
		return noOfCopies;
	}


	/**
	 * @param noOfCopies the noOfCopies to set
	 */
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