package com.fernandoh.service;

import com.fernandoh.dto.ClientDTO;
import com.fernandoh.dto.DepositMoneyDTO;
import com.fernandoh.dto.UpdateClientDTO;
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

    public Client updateClient(UpdateClientDTO clientDTO, Long id) throws Exception {
        Client client = getClient(id);

        client.setFirstName(clientDTO.firstName());
        client.setLastName(clientDTO.lastName());

        saveClient(client);

        return client;
    }

    public Client depositMoney(DepositMoneyDTO dto) throws Exception {
        Client client = getClient(dto.id());

        client.setBalance(client.getBalance().add(dto.value()));

        saveClient(client);

        return client;
    }

    public void deleteUser(Long id) throws Exception {
        Client client = getClient(id);

        deleteClient(client);
    }

    private Client getClient(Long id) throws Exception {
        return clientRepository.findById(id).map(Client::new)
                .orElseThrow(() -> new Exception("Cliente não consta em nossa base."));
    }

    private void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    private void saveClient(Client client) {
        clientRepository.save(client);
    }
}
