package com.example.ShopAPI.services;

import com.example.ShopAPI.DTOs.ClientRequestDto;
import com.example.ShopAPI.DTOs.ClientResponseDto;
import com.example.ShopAPI.DTOs.AddressUpdateDTO;
import com.example.ShopAPI.mappers.ClientMapper;
import com.example.ShopAPI.models.Address;
import com.example.ShopAPI.models.Client;
import com.example.ShopAPI.repositories.AddressRepository;
import com.example.ShopAPI.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Transactional
    public ClientResponseDto createClient(ClientRequestDto clientRequestDto) {
        Client client = clientMapper.clientRequestDtoToClient(clientRequestDto);

        Address address = addressRepository.findById(clientRequestDto.getAddressId())
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + clientRequestDto.getAddressId()));

        client.setAddress(address); // связанный объект Address
        Client savedClient = clientRepository.save(client);

        return clientMapper.clientToClientResponseDto(savedClient);
    }

    @Transactional
    public void deleteClient(UUID id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + id));
        clientRepository.delete(client);
    }

    public List<ClientResponseDto> getClientsByNameAndSurname(String clientName, String clientSurname) {
        List<Client> clients = clientRepository.findByClientNameAndClientSurname(clientName, clientSurname);
        return clients.stream()
                .map(clientMapper::clientToClientResponseDto)
                .collect(Collectors.toList());
    }

    public List<ClientResponseDto> getAllClients(Optional<Integer> limit, Optional<Integer> offset) {
        int page = offset.orElse(0);
        int pageSize = limit.orElse(10);

        if (limit.isPresent() || offset.isPresent()) {
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<Client> clientPage = clientRepository.findAll(pageable);
            return clientMapper.toDtoList(clientPage.getContent());
        }
        else {
            return clientMapper.toDtoList(clientRepository.findAll());
        }
    }

    @Transactional
    public ClientResponseDto updateClientAddress(UUID id, AddressUpdateDTO updateAddressDTO) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + id));
        Address newAddress = addressRepository.findById(updateAddressDTO.getAddressId()).orElseThrow(() -> new RuntimeException("Address not found with id: " + updateAddressDTO.getAddressId()));
        client.setAddress(newAddress);
        Client updatedClient = clientRepository.save(client);
        return clientMapper.clientToClientResponseDto(updatedClient);
    }

    public boolean exists(UUID id){
        return clientRepository.existsById(id);
    }

}
