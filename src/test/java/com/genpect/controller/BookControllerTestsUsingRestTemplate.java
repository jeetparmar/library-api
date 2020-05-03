package com.genpect.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.genpect.domain.Book;
import com.genpect.service.BookService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookControllerTestsUsingRestTemplate {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private BookService bookService;

	@Test
	public void findAllTest() throws Exception {
		List<Book> list = new ArrayList<>();
		list.add(new Book(1, "test title 1", "test auther 1", 123456, 100.0, "english"));
		ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:" + port + "/library/books",
				List.class);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void findByIdTest() throws Exception {
		ResponseEntity<Book> response = restTemplate.getForEntity("http://localhost:" + port + "/library/books/1",
				Book.class);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void saveTest() throws Exception {
		Book book = new Book(null, "Test Title", "Test Author", 123456, 100.00, "English");
		ResponseEntity<Book> response = restTemplate.postForEntity("http://localhost:" + port + "/library/books", book,
				Book.class);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void updateTest() throws Exception {
		String url = "http://localhost:" + port + "/library/books";

		Book book = new Book(1, "Test Title", "Test Author", 123456, 100.00, "English");
		restTemplate.put(url, book);
		ResponseEntity<Book> response = restTemplate.exchange(RequestEntity.put(new URI(url)).body(book), Book.class);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void deleteByIdTest() throws Exception {
		Long bookId = 1L;
		String url = "http://localhost:" + port + "/library/books/{bookId}";
		restTemplate.delete("http://localhost:" + port + "/library/books", bookId);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, String.class,
				bookId);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
}
