package com.ss.training.spring.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.training.spring.lms.entity.Genre;

public class GenreDAO extends BaseDAO<Genre> {

	public GenreDAO(Connection conn) {
		super(conn);
	}

	public Integer addGenre(Genre genre) throws ClassNotFoundException, SQLException {
		return saveWithPK("INSERT INTO tbl_genre (genre_name) VALUES (?)", new Object[] { genre.getGenreName() });
	}

	public void addBookGenreRelationship(Integer genre_id, Integer bookId) throws SQLException, ClassNotFoundException {

		// create a statement Object
		save("INSERT INTO tbl_book_genres (genre_id, bookId) VALUES (?, ?)", new Object[] { genre_id, bookId });
	}

	public void updateGenre(Genre genre) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_genre SET genre_name=? WHERE genre_id=?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_genre WHERE genre_id=?", new Object[] { genre.getGenreId() });
	}

	public List<Genre> readAllGenres() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_genre", null);
	}

	/**
	 * Returns a list of genres identified by genreId; exactly one genre should be
	 * returned in the List since genreId is a unique key value
	 */
	public List<Genre> readGenresById(Integer genreId) throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_genre WHERE genre_id=?", new Object[] { genreId });
	}

	/**
	 * Returns a list of genres identified by name; usually, only one genre will be
	 * returned in the List
	 */
	public List<Genre> readGenresByName(String genreName) throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_genre WHERE genre_name=?", new Object[] { genreName });
	}

	/**
	 * Returns a list of genres associated with a bookId
	 * 
	 * @param bookId key of book to return its associated genres
	 * @return a list of Genres associated with the bookId
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Genre> readGenresByBookId(Integer bookId) throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_genre WHERE genre_id IN (SELECT genre_id FROM tbl_book_genres WHERE bookId=?)",
				new Object[] { bookId });
	}

	/**
	 * Returns a list of genres NOT associated with a bookId
	 * 
	 * @param bookId key of book to return genres not currently associated with it
	 * @return a list of Genres not currently associated with the bookId
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Genre> readGenresByNotBookId(Integer bookId) throws SQLException, ClassNotFoundException {
		return read(
				"SELECT * FROM tbl_genre WHERE NOT genre_id IN (SELECT genre_id FROM tbl_book_genres WHERE bookId=?)",
				new Object[] { bookId });
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<>();
		while (rs.next()) {
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			genres.add(genre);
		}
		return genres;
	}

}
