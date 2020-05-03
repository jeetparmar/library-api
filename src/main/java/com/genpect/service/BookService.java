package com.genpect.service;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpect.domain.Book;
import com.genpect.repository.BookRepository;

@Service
public class BookService {
	
	private static Logger log = LoggerFactory.getLogger(BookService.class);
	
	@Autowired
	private BookRepository bookRepository;

	public List<Book> findAll() {
		log.info("BookService - findAll");
		return bookRepository.findAll();
	}

	public Book findById(Long id) {
		log.info("BookService - findById");
		return bookRepository.findById(id).get();
	}

	public Book saveOrUpdate(Book book) {
		log.info("BookService - saveOrUpdate");
		return bookRepository.saveAndFlush(book);
	}

	public String deleteById(Long id) {
		log.info("BookService - deleteById");
		JSONObject jsonObject = new JSONObject();
		try {
			bookRepository.deleteById(id);
			jsonObject.put("message", "Book deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

}
