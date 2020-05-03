package com.genpect.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genpect.domain.Book;
import com.genpect.service.BookService;

@WebMvcTest(BookController.class)
public class BookControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;
	
	@Autowired
    private ObjectMapper objectMapper;

	@Test
	public void findAllTest() throws Exception {
		List<Book> list = new ArrayList<>();
		list.add(new Book(1, "test title 1", "test auther 1", 123456, 100.0, "english"));
		when(bookService.findAll()).thenReturn(list);
		this.mockMvc.perform(get("/books")).andExpect(status().isOk()).andExpect(content().string(
				"[{\"id\":1,\"title\":\"test title 1\",\"author\":\"test auther 1\",\"isbnNumber\":123456,\"price\":100.0,\"language\":\"english\"}]"));
	}

	@Test
	public void findByIdTest() throws Exception {
		Book book = new Book(1, "Test Title", "Test Author", 123456, 100.00, "English");
		given(bookService.findById(1L)).willReturn(book);
		this.mockMvc.perform(get("/books/1")).andExpect(status().isOk())
			.andExpect(jsonPath("$.title", is(book.getTitle())))
			.andExpect(jsonPath("$.author", is(book.getAuthor())))
			.andExpect(jsonPath("$.isbnNumber", is(book.getIsbnNumber())))
			.andExpect(jsonPath("$.price", is(book.getPrice())))
			.andExpect(jsonPath("$.language", is(book.getLanguage())));
	}

	@Test
	public void saveTest() throws Exception {
		Book book = new Book(null, "Test Title", "Test Author", 123456, 100.00, "English");
		when(bookService.saveOrUpdate(book)).thenReturn(book);
		this.mockMvc.perform(post("/books")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(book)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void updateTest() throws Exception {
		Book book = new Book(1, "Test Title", "Test Author", 123456, 200.00, "English");
		when(bookService.saveOrUpdate(book)).thenReturn(book);
		this.mockMvc.perform(put("/books")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(book)))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteByIdTest() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "Book deleted successfully");

		when(bookService.deleteById(1L)).thenReturn(jsonObject.toString());
		this.mockMvc.perform(delete("/books/1")).andExpect(status().isOk())
				.andExpect(content().string("{\"message\":\"Book deleted successfully\"}"));
	}
}
