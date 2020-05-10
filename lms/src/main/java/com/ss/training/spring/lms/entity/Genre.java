package com.ss.training.spring.lms.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_genre")
public class Genre implements Serializable {

	private static final long serialVersionUID = 8808881974962129912L;

	@Id
	@Column(name = "genre_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long genreId;

	@Column(name = "genreName")
	private String genreName;

	@ManyToMany(mappedBy = "genres")
	private List<Book> books;

	public Long getGenreId() {
		return genreId;
	}

	public void setGenreId(Long genreId) {
		this.genreId = genreId;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public int hashCode() {
		return Objects.hash(books, genreId, genreName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Genre)) {
			return false;
		}
		Genre other = (Genre) obj;
		return Objects.equals(books, other.books) && Objects.equals(genreId, other.genreId)
				&& Objects.equals(genreName, other.genreName);
	}

}
