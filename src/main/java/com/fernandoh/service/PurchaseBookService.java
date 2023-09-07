package com.fernandoh.service;

import com.fernandoh.dto.PurchaseDTO;
import com.fernandoh.model.Book;
import com.fernandoh.model.Client;
import com.fernandoh.repository.BookRepository;
import com.fernandoh.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseBookService {

    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;

    public PurchaseBookService(ClientRepository clientRepository,
                               BookRepository bookRepository) {
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
    }

    public ResponseEntity<Client> purchaseBook(PurchaseDTO purchaseDTO) throws Exception {
        Book book = bookRepository.findById(purchaseDTO.bookdId()).map(Book::new)
                .orElseThrow(() -> new Exception("Livro não consta em nossa base."));

        Client client = clientRepository.findById(purchaseDTO.clientId()).map(Client::new)
                .orElseThrow(() -> new Exception("Cliente não consta em nossa base."));

        if(book.getPrice().compareTo(client.getBalance()) > 0) throw new Exception("Cliente não tem saldo suficiente");

        client.addBook(book);
        clientRepository.save(client);

        return ResponseEntity.ok(client);
    }
}