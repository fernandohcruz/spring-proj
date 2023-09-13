package com.fernandoh.controller;

import com.fernandoh.dto.PurchaseDTO;
import com.fernandoh.model.Client;
import com.fernandoh.service.PurchaseBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("purchase-book")
public class PurchaseBookController {

    private final PurchaseBookService purchaseBookService;

    public PurchaseBookController(PurchaseBookService purchaseBookService) {
        this.purchaseBookService = purchaseBookService;
    }

    @PostMapping
    public ResponseEntity<Client> purchaseBook(@RequestBody PurchaseDTO purchaseDTO) throws Exception {
        Client client = purchaseBookService.purchaseBook(purchaseDTO);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
}
