package com.fernandoh.controller;

import com.fernandoh.dto.ClientDTO;
import com.fernandoh.dto.DepositMoneyDTO;
import com.fernandoh.dto.UpdateClientDTO;
import com.fernandoh.model.Client;
import com.fernandoh.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody ClientDTO clientDTO) {
        Client client = clientService.createClient(clientDTO);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PostMapping("/deposit-money")
    public ResponseEntity<Client> depositMoney(@RequestBody DepositMoneyDTO dto) throws Exception {
        Client client = clientService.depositMoney(dto);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<Client> updateClient(@RequestBody UpdateClientDTO clientDTO, @PathVariable Long id) throws Exception {
        Client client = clientService.updateClient(clientDTO, id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws Exception {
        clientService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
