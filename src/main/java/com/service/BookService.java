package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.domain.Book;
import com.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
    private BookRepository bookRepository;
	
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    // try to find by id
    // if success return book
    // catch EntityNotFoundException
    // return null
    public Book getBook(long id) {
    		return bookRepository.getOne(id);
    }

    // try to delete
    // if success return true
    // catch IllegalArgumentException
    // return false
	public boolean deleteBook(long id) {
		bookRepository.deleteById(id);
		
		return true;
	}

	// try to save
	// if success return true
	// catch IllegalArgumentException
    // return false
	public boolean addBook(Book book) {
		return bookRepository.save(book) != null;
	}
	
	public List<Book> getBooksByAuthor(String author){
		
		return bookRepository.findByAuthor(author);
	}
    
    
    
	
}
