package com.example.demo.service.impl;

import com.example.demo.domain.BookEntity;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createUpdateBook(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn((isbn));
        return bookRepository.save(bookEntity);
    }

    @Override
    public Optional<BookEntity> findOne(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public boolean isExists(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public List<BookEntity> findAll() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public BookEntity partialUpdate(String isbn, BookEntity bookEntity) {
        if (!bookRepository.existsById(isbn)) throw new RuntimeException("Book doesn't exists");
        return bookRepository.findById(isbn).map(existingBookEntity -> {
            Optional.ofNullable(bookEntity.getAuthorEntity()).ifPresent(existingBookEntity::setAuthorEntity);
            Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingBookEntity::setTitle);
            return bookRepository.save(existingBookEntity);
        }).orElseThrow(() -> new RuntimeException("Book doesnot exists"));

    }

    @Override
    public void delete(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
