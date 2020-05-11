package com.ss.training.spring.lms.entity;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BookLoansKey implements Serializable {

	private static final long serialVersionUID = -2149958063479848070L;

	@Column(name = "bookId")
	private Long bookId;

	@Column(name = "branchId")
	private Long branchId;

	@Column(name = "cardNo")
	private Long cardNo;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getCardNo() {
		return cardNo;
	}

	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookId, branchId, cardNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BookLoansKey)) {
			return false;
		}
		BookLoansKey other = (BookLoansKey) obj;
		return Objects.equals(bookId, other.bookId) && Objects.equals(branchId, other.branchId)
				&& Objects.equals(cardNo, other.cardNo);
	}

}