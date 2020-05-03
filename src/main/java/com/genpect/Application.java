package com.genpect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.genpect.domain.Book;
import com.genpect.service.BookService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private BookService service;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		service.saveOrUpdate(new Book(null, "Test Title", "Test Author", 123456, 100.00, "English"));
	}

}
