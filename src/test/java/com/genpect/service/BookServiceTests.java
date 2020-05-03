package com.genpect.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.genpect.domain.Book;
import com.genpect.repository.BookRepository;


@ExtendWith(MockitoExtension.class)
public class BookServiceTests {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;

	@Test
	public void findAllTest() {
		List<Book> list = new ArrayList<Book>();
		list.add(new Book(null, "Test Title", "Test Author", 123456, 100.00, "English"));
		when(bookRepository.findAll()).thenReturn(list);
		assertEquals(1, bookService.findAll().size());
		verify(bookRepository, times(1)).findAll();
	}

	@Test
	public void findByIdTest() {
		Long id = 1L;
		Book book = new Book(1, "Test Title", "Test Author", 123456, 100.00, "English");
		given(bookRepository.findById(id)).willReturn(Optional.of(book));
		Book expectedBook = bookService.findById(id);
		assertThat(expectedBook).isNotNull();
		assertEquals("Test Title", bookService.findById(id).getTitle());
	}

	@Test
	public void deleteByIdTest() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "Book deleted successfully");

		Long id = 1L;
		bookService.deleteById(id);
		verify(bookRepository, times(1)).deleteById(id);
		assertEquals(jsonObject.get("message"), new JSONObject(bookService.deleteById(id)).get("message"));
	}

	@Test
	public void saveOrUpdateTest() throws JSONException {
		Book book = new Book(null, "Test Title", "Test Author", 123456, 100.00, "English");
		given(bookRepository.save(book)).willReturn(book);
		Book expectedBook = bookService.saveOrUpdate(book);
		assertThat(expectedBook).isNotNull();
		verify(bookRepository).save(any(Book.class));
	}
}
