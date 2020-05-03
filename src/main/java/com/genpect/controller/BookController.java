package com.genpect.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genpect.domain.Book;
import com.genpect.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	private static Logger log = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	@GetMapping
	public ResponseEntity<List<Book>> findAll() {
		log.info("BookController - findAll");
		return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<Book> findById(@PathVariable Long id) {
		log.info("BookController - findById");
		return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> save(@RequestBody Book book) {
		log.info("BookController - save");
		return new ResponseEntity<>(bookService.saveOrUpdate(book), HttpStatus.CREATED);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> update(@RequestBody Book book) {
		log.info("BookController - update");
		return new ResponseEntity<>(bookService.saveOrUpdate(book), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		log.info("BookController - deleteById");
		return new ResponseEntity<>(bookService.deleteById(id), HttpStatus.OK);
	}

}
