package com.fernandoh.service;

import com.fernandoh.dto.BookDTO;
import com.fernandoh.model.Book;
import com.fernandoh.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public ResponseEntity<Book> createBook(BookDTO bookDTO) {
        Book book = new Book(bookDTO);
        saveBook(book);
        return ResponseEntity.ok(book);
    }

    private void saveBook(Book book){
        bookRepository.save(book);
    }
}
