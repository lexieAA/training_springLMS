package com.ss.training.spring.lms.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.training.spring.lms.entity.Book;
import com.ss.training.spring.lms.entity.BookCopies;
import com.ss.training.spring.lms.entity.BookCopiesKey;
import com.ss.training.spring.lms.entity.BookLoan;
import com.ss.training.spring.lms.entity.BookLoansKey;

@Repository
public interface BookLoanDAO extends JpaRepository<BookLoan, Long>{
	Optional<BookLoan> findById(BookLoansKey id);
}