package com.example.demo.service;

import com.example.demo.domain.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity createUpdateBook(String isbn, BookEntity bookEntity);

    Optional<BookEntity> findOne(String isbn);

    boolean isExists(String isbn);

    List<BookEntity> findAll();

    BookEntity partialUpdate(String isbn, BookEntity bookEntity);

    void delete(String isbn);
}
