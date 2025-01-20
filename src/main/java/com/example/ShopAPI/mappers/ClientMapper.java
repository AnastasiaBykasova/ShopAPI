package com.example.ShopAPI.mappers;
import com.example.ShopAPI.DTOs.ClientDto;
import com.example.ShopAPI.models.Address;
import com.example.ShopAPI.models.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

import com.example.ShopAPI.DTOs.ClientRequestDto;
import com.example.ShopAPI.DTOs.ClientResponseDto;
import com.example.ShopAPI.models.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(source = "address.id", target = "addressId")
    ClientResponseDto clientToClientResponseDto(Client client);

    @Mapping(source = "addressId", target = "address.id")
    Client clientRequestDtoToClient(ClientRequestDto clientRequestDTO);
}





//@Mapper(componentModel = "spring")
//public interface ClientMapper {
//
//    @Mapping(source = "addressId", target = "addressId")
//    ClientDto clientToClientDto(Client client);
//
//    @Mapping(source = "addressId", target = "addressId")
//    Client clientDtoToClient(ClientDto clientDto);
//
//}
