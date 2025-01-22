package com.example.ShopAPI.mappers;

import com.example.ShopAPI.DTOs.*;
import com.example.ShopAPI.models.Client;
import com.example.ShopAPI.models.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierResponseDto supplierToSupplierResponseDto(Supplier supplier);

    @Mapping(source = "addressId", target = "address.id")
    Supplier supplierRequestDtoToSupplier(SupplierRequestDto supplierRequestDTO);

    List<SupplierResponseDto> toDtoList(List<Supplier> suppliers);
}
