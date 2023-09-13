package com.fernandoh.service;

import com.fernandoh.dto.ClientDTO;
import com.fernandoh.dto.DepositeMoneyDTO;
import com.fernandoh.model.Client;
import com.fernandoh.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client createClient(ClientDTO clientDTO) {
        Client client = new Client(clientDTO);
        saveClient(client);
        return client;
    }

    public Client depositMoney(DepositeMoneyDTO dto) throws Exception {
        Client client = clientRepository.findById(dto.id()).map(Client::new)
                .orElseThrow(() -> new Exception("Cliente não consta em nossa base."));

        client.deposit(dto.value());
        saveClient(client);

        return client;
    }

    private void saveClient(Client client) {
        clientRepository.save(client);
    }
}
