package com.example.ShopAPI.mappers;

import com.example.ShopAPI.DTOs.AddressDto;
import com.example.ShopAPI.models.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDto addressToAddressDto(Address address);
    Address addressDtoToAddress(AddressDto addressDto);
}
