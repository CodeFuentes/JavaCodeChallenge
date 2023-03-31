package com.codefuentes.javacodechallenge;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import com.codefuentes.javacodechallenge.model.Client;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final AmqpTemplate amqpTemplate;

    public ClientService(ClientRepository clientRepository, AmqpTemplate amqpTemplate) {
        this.clientRepository = clientRepository;
        this.amqpTemplate = amqpTemplate;
    }

    public Client createClient(Client client) {
        Optional<Client> existingClient = clientRepository.findByUserIdAndRoomId(client.getUserId(), client.getRoomId());
        if (existingClient.isPresent()) {
            Client updatedClient = existingClient.get();
            updatedClient.setStatus(client.getStatus());
            amqpTemplate.convertAndSend("client.updated", updatedClient);
            return clientRepository.save(updatedClient);
        } else {
            amqpTemplate.convertAndSend("client.created", client);
            return clientRepository.save(client);
        }
    }

    public Optional<Client> getClient(UUID clientId) {
        return clientRepository.findById(clientId);
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public void deleteClient(UUID clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isPresent()) {
            clientRepository.delete(client.get());
            amqpTemplate.convertAndSend("client.deleted", client.get());
        }
    }

    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }
}
