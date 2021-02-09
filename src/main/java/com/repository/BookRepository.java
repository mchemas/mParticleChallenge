package com.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findByAuthor(String author);

}
