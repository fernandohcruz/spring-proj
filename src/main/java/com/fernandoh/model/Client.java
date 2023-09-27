package com.fernandoh.model;

import com.fernandoh.dto.ClientDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String document;
    private BigDecimal balance;
    @ManyToMany
    private List<Book> books;

    public Client() {
    }

    public Client(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.document = client.getDocument();
        this.balance = client.getBalance();
        this.books = client.getBooks();
    }

    public Client(ClientDTO clientDTO) {
        this.firstName = clientDTO.firstName();
        this.lastName = clientDTO.lastName();
        this.document = clientDTO.document();
        this.balance = clientDTO.balance();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", document='" + document + '\'' +
                ", balance=" + balance +
                ", books=" + books +
                '}';
    }
}
