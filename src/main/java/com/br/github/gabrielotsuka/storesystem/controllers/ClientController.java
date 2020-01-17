package com.br.github.gabrielotsuka.storesystem.controllers;

import com.br.github.gabrielotsuka.storesystem.controllers.request.ClientRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.response.ClientResponse;
import com.br.github.gabrielotsuka.storesystem.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientResponse> save(@RequestBody @Valid ClientRequest client){
        return clientService.save(client.toClient());
    }

    @GetMapping
    public List<ClientResponse> getClients(){
        return clientService.getClients();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable(value = "id") Long id){
        return clientService.getClientById(id);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<ClientResponse> editClient(@PathVariable(value = "id") Long id,
                                                     @RequestBody @Valid ClientRequest newClient){
        return clientService.editClient(id, newClient);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") Long id){
        return clientService.deleteClient(id);
    }
}
