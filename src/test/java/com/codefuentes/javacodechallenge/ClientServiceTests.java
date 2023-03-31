package com.codefuentes.javacodechallenge;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.codefuentes.javacodechallenge.model.Client;

public class ClientServiceTests {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateClient() {
        UUID userId = UUID.randomUUID();
        UUID roomId = UUID.randomUUID();

        Client client = new Client();
        client.setUserId(userId);
        client.setRoomId(roomId);

        when(clientRepository.save(client)).thenReturn(client);

        Client createdClient = clientService.createClient(client);

        verify(clientRepository, times(1)).save(client);

        Assertions.assertEquals(userId, createdClient.getUserId());
        Assertions.assertEquals(roomId, createdClient.getRoomId());
        Assertions.assertNotNull(createdClient.getClientId());
    }

    @Test
    public void testUpdateClient() {
        UUID userId = UUID.randomUUID();
        UUID roomId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();

        Client client = new Client();
        client.setUserId(userId);
        client.setRoomId(roomId);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);

        Client updatedClient = clientService.updateClient(client);

        verify(clientRepository, times(1)).findById(clientId);
        verify(clientRepository, times(1)).save(client);

        Assertions.assertEquals(userId, updatedClient.getUserId());
        Assertions.assertEquals(roomId, updatedClient.getRoomId());
        Assertions.assertEquals(clientId, updatedClient.getClientId());
    }

    @Test
    public void testDeleteClient() {
        UUID clientId = UUID.randomUUID();

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(new Client()));

        clientService.deleteClient(clientId);

        verify(clientRepository, times(1)).findById(clientId);
        verify(clientRepository, times(1)).deleteById(clientId);
    }

    @Test
    public void testGetAllClients() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client());
        clients.add(new Client());

        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> allClients = clientService.getClients();

        verify(clientRepository, times(1)).findAll();

        Assertions.assertEquals(clients.size(), allClients.size());
    }

    @Test
    public void testGetClientById() {
        UUID clientId = UUID.randomUUID();

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(new Client()));

        Optional<Client> client = clientService.getClient(clientId);

        verify(clientRepository, times(1)).findById(clientId);

        Assertions.assertNotNull(client);
    }
}
