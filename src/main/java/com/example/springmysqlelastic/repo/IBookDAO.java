package com.example.springmysqlelastic.repo;

import com.example.springmysqlelastic.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookDAO extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
}
