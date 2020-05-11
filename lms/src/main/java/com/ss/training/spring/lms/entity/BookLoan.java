/**
 * 
 */
package com.ss.training.spring.lms.entity;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_book_loans")
public class BookLoan implements Serializable {

	private static final long serialVersionUID = -326651163829760200L;

	@EmbeddedId
	BookLoansKey id;

	@Column(name = "dateOut")
	private Date dateOut;

	@Column(name = "dueDate")
	private Date dueDate;

	@Column(name = "dateIn")
	private Date dateIn;
	
	public BookLoan() {
		
	}

	public BookLoan(BookLoansKey id, Date dateOut, Date dueDate, Date dateIn) {
		super();
		this.id = id;
		this.dateOut = dateOut;
		this.dueDate = dueDate;
		this.dateIn = dateIn;
	}

	public BookLoansKey getId() {
		return id;
	}

	public void setId(BookLoansKey id) {
		this.id = id;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
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
		if (!(obj instanceof BookLoan)) {
			return false;
		}
		BookLoan other = (BookLoan) obj;
		return Objects.equals(id, other.id);
	}

}