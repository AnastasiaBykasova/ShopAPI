package com.example.ShopAPI.mappers;

import com.example.ShopAPI.DTOs.SupplierDto;
import com.example.ShopAPI.models.Supplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierDto supplierToSupplierDto(Supplier supplier);
    Supplier supplierDtoToSupplier(SupplierDto supplierDto);
}
