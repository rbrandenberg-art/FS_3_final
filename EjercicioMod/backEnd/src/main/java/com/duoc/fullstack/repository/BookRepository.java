package com.duoc.fullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.fullstack.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}
