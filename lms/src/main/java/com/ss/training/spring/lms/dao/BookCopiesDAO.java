package com.ss.training.spring.lms.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.training.spring.lms.entity.BookCopies;
import com.ss.training.spring.lms.entity.BookCopiesKey;

@Repository
public interface BookCopiesDAO extends JpaRepository<BookCopies, BookCopiesKey> {
	List<BookCopies> findByIdBranchId(Long branchId);

	Optional<BookCopies> findById(BookCopiesKey id);
}
