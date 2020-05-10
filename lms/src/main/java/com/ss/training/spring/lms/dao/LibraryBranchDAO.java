package com.ss.training.spring.lms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.training.spring.lms.entity.LibraryBranch;

@Repository
public interface LibraryBranchDAO extends JpaRepository<LibraryBranch, Long> {

}
