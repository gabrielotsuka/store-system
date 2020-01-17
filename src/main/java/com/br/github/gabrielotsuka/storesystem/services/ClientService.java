package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.controllers.request.ClientRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.response.ClientResponse;
import com.br.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import com.br.github.gabrielotsuka.storesystem.models.Client;
import com.br.github.gabrielotsuka.storesystem.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ResponseEntity<ClientResponse> save(Client client) {
        clientRepository.save(client);
        return new ResponseEntity<ClientResponse>(ClientResponse.toResponse(client), HttpStatus.CREATED);
    }

    public List<ClientResponse> getClients() {
        List<Client> arr = clientRepository.findAll();
        List<ClientResponse> response = new ArrayList<>();
        arr.forEach(temp -> response.add(ClientResponse.toResponse(temp)));
        return response;
    }

    public ResponseEntity<ClientResponse> getClientById(Long id) {
        Client client = verifyClientExistence(id);
        return new ResponseEntity<ClientResponse>(ClientResponse.toResponse(client), HttpStatus.OK);
    }

    public ResponseEntity<ClientResponse> editClient(Long id, ClientRequest newClient) {
        Client oldClient = verifyClientExistence(id);
        oldClient.setEmail(newClient.getEmail());
        oldClient.setName(newClient.getName());
        oldClient.setPwd(newClient.getPwd());
        clientRepository.save(oldClient);
        return new ResponseEntity<ClientResponse>(ClientResponse.toResponse(oldClient), HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteClient(Long id) {
        Client client = verifyClientExistence(id);
        clientRepository.delete(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Client verifyClientExistence(Long id){
        Optional<Client> client = clientRepository.findById(id);
        if(!client.isPresent())
            throw new ResourceNotFoundException("Client not found. ID: " + id);
        else
            return client.get();
    }
}
