package com.fernandoh.controller;

import com.fernandoh.dto.BookDTO;
import com.fernandoh.dto.UpdateBookDTO;
import com.fernandoh.model.Book;
import com.fernandoh.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> bookList = bookService.getAllBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDTO bookDTO) {
        Book book = bookService.createBook(bookDTO);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update-book/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody UpdateBookDTO bookDTO, Long id) throws Exception {
        Book book = bookService.updateBook(bookDTO, id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) throws Exception {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
