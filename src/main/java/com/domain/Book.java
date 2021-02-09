package com.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO Fill out tihs object to represent the JSON Book object based on this sample:
 *
 * {
 *     "book_id": 12345,
 *     "title": "My Book's Title",
 *     "author": "Person Lastname",
 *     "published_date": DATE
 * }
 */

@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Book implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name = "id", nullable = false)
	@GeneratedValue
	private long book_id;
	private String title;
	private String author;
	private Date date;
	
	public Book() {}
	
	public Book(String title, String author) {
		this.setTitle(title);
		this.setAuthor(author);
		this.setDate(new Date());
	}

	public long getBook_id() {
		return book_id;
	}

	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", title=" + title + ", author=" + author + ", date=" + date + "]";
	}
	
}
