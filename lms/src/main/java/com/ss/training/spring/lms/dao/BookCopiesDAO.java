package com.ss.training.spring.lms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.training.spring.lms.entity.BookCopies;

@Repository
public interface BookCopiesDAO extends JpaRepository<BookCopies, Long>{
}
