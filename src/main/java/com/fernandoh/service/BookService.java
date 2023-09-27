package com.fernandoh.service;

import com.fernandoh.dto.BookDTO;
import com.fernandoh.model.Book;
import com.fernandoh.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book createBook(BookDTO bookDTO) {
        Book book = new Book(bookDTO);
        saveBook(book);
        return book;
    }

    private void saveBook(Book book) {
        bookRepository.save(book);
    }
}
