package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//import org.springframework.scheduling.annotation.Async;
//
//import java.util.concurrent.CompletableFuture;

import com.domain.Book;
import com.repository.BookRepository;

/**
 * This should be a client that talks to the server hosted at BaseServerUrl. It is all async, and meant
 * to be consumed by theoretical third-parties as a library. User authorization is handled by sending an
 * X-Api-Key header with the value provided to you. This is unique to you, and represents the user
 * who is storing and retrieving books. Each method in the library represents a discrete function
 * provided by the server API. The details required by the HTTP server are provided in the method documentation.
 */
@SpringBootApplication
public class BookclientApplication {
	private final static String BASE_SERVER_URL = "https://zqev4yu2yk.execute-api.us-west-2.amazonaws.com/prod";
    private final static String API_KEY = "96YtxZBC0U105bnul1gC54e7H4U5ROSP6UEbRcOm"; 
    // TODO Fill this in based on the API Key provided to you in the email. This is the value for the X-Api-Key header.

	public static void main(String[] args) {
		SpringApplication.run(BookclientApplication.class, args);
	}
	
	@Bean
    CommandLineRunner runner(BookRepository bookRepository) {
	    	return args -> {
			
		    	Book bk = new Book("The Windup Girl", "Paolo Bacigalupi");
		    	bookRepository.save(bk);
		    	
		    	Book book = new Book("Dummy Book", "Mike");
		    	bookRepository.save(book);
		    	
	    	};
    }

}
