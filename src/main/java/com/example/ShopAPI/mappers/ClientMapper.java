package com.example.ShopAPI.mappers;

import com.example.ShopAPI.models.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.ShopAPI.DTOs.ClientRequestDto;
import com.example.ShopAPI.DTOs.ClientResponseDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientResponseDto clientToClientResponseDto(Client client);

    @Mapping(source = "addressId", target = "address.id")
    Client clientRequestDtoToClient(ClientRequestDto clientRequestDTO);

    List<ClientResponseDto> toDtoList(List<Client> clients);
}
