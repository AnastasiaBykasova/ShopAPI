package com.example.ShopAPI.services;

import com.example.ShopAPI.DTOs.ClientRequestDto;
import com.example.ShopAPI.DTOs.ClientResponseDto;
import com.example.ShopAPI.mappers.ClientMapper;
import com.example.ShopAPI.models.Address;
import com.example.ShopAPI.models.Client;
import com.example.ShopAPI.repositories.AddressRepository;
import com.example.ShopAPI.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, AddressRepository addressRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.clientMapper = clientMapper;
    }

    public ClientResponseDto createClient(ClientRequestDto clientRequestDTO) {
        Client client = clientMapper.clientRequestDtoToClient(clientRequestDTO);

        Address address = addressRepository.findById(clientRequestDTO.getAddressId())
                .orElseThrow(() -> new IllegalArgumentException("Address not found with id: " + clientRequestDTO.getAddressId()));

        client.setAddress(address); // связанный объект Address
        Client savedClient = clientRepository.save(client);

        return clientMapper.clientToClientResponseDto(savedClient);
    }

    public void deleteClient(UUID id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + id));
        clientRepository.delete(client);
    }
}
