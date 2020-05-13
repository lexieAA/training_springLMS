package com.ss.training.spring.lms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tbl_library_branch")
public class LibraryBranch implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2251924269768951050L;

	/**
	 * 
	 */

	@Id
	@Column(name = "branchId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long branchId;

	@Column(name = "branchName")
	private String branchName;

	@Column(name = "branchAddress")
	private String branchAddress;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "branch")
	@JsonBackReference(value="branchCopies")
	private List<BookCopies> bookCopies;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "branch")
	@JsonBackReference(value="branchLoan")
	private List<BookLoan> bookLoan;

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public List<BookCopies> getBookCopies() {
		return bookCopies;
	}

	public void setBookCopies(List<BookCopies> bookCopies) {
		this.bookCopies = bookCopies;
	}

	public List<BookLoan> getBookLoan() {
		return bookLoan;
	}

	public void setBookLoan(List<BookLoan> bookLoan) {
		this.bookLoan = bookLoan;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookCopies == null) ? 0 : bookCopies.hashCode());
		result = prime * result + ((bookLoan == null) ? 0 : bookLoan.hashCode());
		result = prime * result + ((branchAddress == null) ? 0 : branchAddress.hashCode());
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
		result = prime * result + ((branchName == null) ? 0 : branchName.hashCode());
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
		LibraryBranch other = (LibraryBranch) obj;
		if (bookCopies == null) {
			if (other.bookCopies != null)
				return false;
		} else if (!bookCopies.equals(other.bookCopies))
			return false;
		if (bookLoan == null) {
			if (other.bookLoan != null)
				return false;
		} else if (!bookLoan.equals(other.bookLoan))
			return false;
		if (branchAddress == null) {
			if (other.branchAddress != null)
				return false;
		} else if (!branchAddress.equals(other.branchAddress))
			return false;
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		if (branchName == null) {
			if (other.branchName != null)
				return false;
		} else if (!branchName.equals(other.branchName))
			return false;
		return true;
	}

}