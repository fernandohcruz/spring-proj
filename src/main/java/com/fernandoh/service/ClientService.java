package com.fernandoh.service;

import com.fernandoh.dto.ClientDTO;
import com.fernandoh.dto.DepositeMoneyDTO;
import com.fernandoh.model.Client;
import com.fernandoh.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ResponseEntity<Client> createClient(ClientDTO clientDTO) {
        Client client = new Client(clientDTO);
        saveCLient(client);
        return ResponseEntity.ok(client);
    }

    public ResponseEntity<Client> depositMoney(DepositeMoneyDTO dto) throws Exception {
        Client client = clientRepository.findById(dto.id()).map(Client::new)
                .orElseThrow(() -> new Exception("Cliente não consta em nossa base."));

        client.deposit(dto.value());
        saveCLient(client);

        return ResponseEntity.ok(client);
    }

    private void saveCLient(Client client) {
        clientRepository.save(client);
    }
}
