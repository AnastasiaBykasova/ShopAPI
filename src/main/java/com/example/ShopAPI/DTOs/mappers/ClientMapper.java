package com.example.ShopAPI.DTOs.mappers;
import com.example.ShopAPI.DTOs.ClientDto;
import com.example.ShopAPI.models.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(source = "addressId", target = "addressId")
    ClientDto clientToClientDto(Client client);
    @Mapping(source = "addressId", target = "addressId")
    Client clientDtoToClient(ClientDto clientDto);
}
