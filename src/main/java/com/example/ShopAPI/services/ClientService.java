package com.example.ShopAPI.services;

import com.example.ShopAPI.DTOs.ClientDto;
import com.example.ShopAPI.configs.NotFoundException;
import com.example.ShopAPI.DTOs.mappers.ClientMapper;
import com.example.ShopAPI.models.Address;
import com.example.ShopAPI.models.Client;
import com.example.ShopAPI.repositories.AddressRepository;
import com.example.ShopAPI.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final AddressRepository addressRepository;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.addressRepository = addressRepository;
    }

    // Добавление клиента (POST /clients):
    public ClientDto createClient(ClientDto clientDto) {
        UUID addressId = clientDto.getAddressId();
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not found with id: " + addressId));
        Client client = clientMapper.clientDtoToClient(clientDto);
        client.setAddressId(address);
        Client createdClient = clientRepository.save(client);
        return clientMapper.clientToClientDto(createdClient);
    }

    // Обновление клиента (PUT /api/v1/clients/{id}):
    public ClientDto updateClient(UUID id, ClientDto clientDto) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + id));
        Address address = addressRepository.findById(clientDto.getAddressId())
                .orElseThrow(() -> new NotFoundException("Address not found with id: " + clientDto.getAddressId()));
        Client client = clientMapper.clientDtoToClient(clientDto);
        client.setId(existingClient.getId());
        client.setAddressId(address);
        Client updatedClient = clientRepository.save(client);
        return clientMapper.clientToClientDto(updatedClient);
    }

    // Получение клиента по id (GET /api/v1/clients/{id}):
    public ClientDto getClientById(UUID id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + id));
        return clientMapper.clientToClientDto(client);
    }



}
