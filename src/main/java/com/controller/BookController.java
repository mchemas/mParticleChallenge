package com.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.Book;
import com.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
    private BookService bookService;
	
	/**
     *  Retrieves a book by its unique ID.
     *
     *      Subpath: /getbook
     *      JSON POST Request Body:
     *      {
     *         "book_id": 12345
     *      }
     *
     *      @param bookId A non-zero positive integer.</param>
     *      @return The book, if found, or else null.</returns>
     */
    @Async
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CompletableFuture<Book> getBookAsync(@PathVariable(value="id") long id) {
    		Book book = bookService.getBook(id);
    		// at the moment throws 500 if not found - should be 404/null

        return CompletableFuture.completedFuture(book);
    }

    /**
     * Retrieves all books saved by this user.
     *
     * Subpath: /getallbooks
     * JSON POST Request Body:
     *  {
     *  }
     *
     * @return Returns all books found, if any, in an array.
     */
    @Async
    @RequestMapping (value = "/", method = RequestMethod.GET)
    public CompletableFuture<List<Book>> GetAllBooksAsync() {
	    	List<Book> books = bookService.getAllBooks();
	    	
	    	return CompletableFuture.completedFuture(books);
    }

    /**
     * Deletes a book.
     *
     * Subpath: /deletebook
     * JSON POST Request Body:
     * {
     *    "book_id": 12345
     * }
     *
     * @param bookId A non-zero positive integer.
     * @return True if a book was actually deleted, or false if it was not.
     */
    @Async
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<Boolean> DeleteBookAsync(@PathVariable(value="id") int id) {
    		boolean delete = bookService.deleteBook(id);
    	
        return CompletableFuture.completedFuture(delete);
    }

    /**
     * Stores a book onto the server
     *
     * Subpath: /addbook
     * {
     *     "book_id": 12345
     * }
     * @param title Required parameter. The title of the book.
     * @param author Required parameter. The author of the book.
     * @return True if a book was actually saved, or false if it was not.
     */
    @Async
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture<Boolean> AddBookAsync(@RequestBody Book book) {
    		boolean added = bookService.addBook(book);
    		
        return CompletableFuture.completedFuture(added);
    }

    /**
     * Gets books from the server, but filters them to only return books that have the specified author.
     *
     * @param author equired parameter. The author of the book.
     * @return A list of books by the author, or an empty list of none were found.
     */
    @Async
    @RequestMapping (value = "/booksFromAuthor", method = RequestMethod.GET)
    public CompletableFuture<List<Book>> GetBooksFromAuthor(@RequestBody Book book) {
    		String author = book.getAuthor();
    	
        return CompletableFuture.completedFuture(bookService.getBooksByAuthor(author));
    }

    /**
     * Gets books from the server, but filters them to only be between the specified date range. Both
     * dates are optional, but at least one must exist in order to return valid results. If both dates are
     * specified, then the books must fall between the two publication dates.
     *
     * @param publishedBefore The last date by which a book must have been published
     * @param publishedAfter The earliest date at which a book must have been published
     * @return Books that meet the criteria of the specified filtering inputs.
     */
    @Async
    public CompletableFuture<Book[]> GetBooksPublishedByDate(Date publishedBefore, Date publishedAfter)
    {
        return CompletableFuture.completedFuture(new Book[0]);
    }
    
}
