package com.fernandoh.service;

import com.fernandoh.dto.BookDTO;
import com.fernandoh.dto.UpdateBookDTO;
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

    public Book updateBook(UpdateBookDTO bookDTO, Long id) throws Exception {
        Book book = getBook(id);

        book.setPrice(bookDTO.price());
        saveBook(book);

        return book;
    }

    public void deleteBook(Long id) throws Exception {
        Book book = getBook(id);

        deleteBook(book);
    }

    private Book getBook(Long id) throws Exception {
        return bookRepository.findById(id).map(Book::new)
                .orElseThrow(() -> new Exception("Livro não consta em nossa base"));
    }

    private void saveBook(Book book) {
        bookRepository.save(book);
    }

    private void deleteBook(Book book) {
        bookRepository.delete(book);
    }
}
