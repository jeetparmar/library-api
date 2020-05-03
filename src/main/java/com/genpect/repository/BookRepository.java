package com.genpect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genpect.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
