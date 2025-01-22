package com.example.ShopAPI.services;

import com.example.ShopAPI.DTOs.*;
import com.example.ShopAPI.mappers.SupplierMapper;
import com.example.ShopAPI.models.Address;
import com.example.ShopAPI.models.Supplier;
import com.example.ShopAPI.repositories.AddressRepository;
import com.example.ShopAPI.repositories.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final AddressRepository addressRepository;
    private final SupplierMapper supplierMapper;

    public SupplierService(SupplierRepository supplierRepository, SupplierMapper supplierMapper, AddressRepository addressRepository) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
        this.addressRepository = addressRepository;
    }

    public SupplierResponseDto createSupplier(SupplierRequestDto supplierRequestDto) {
        Supplier supplier = supplierMapper.supplierRequestDtoToSupplier(supplierRequestDto);

        Address address = addressRepository.findById(supplierRequestDto.getAddressId())
                .orElseThrow(() -> new IllegalArgumentException("Address not found with id: " + supplierRequestDto.getAddressId()));

        supplier.setAddress(address); // связанный объект Address
        Supplier savedSupplier = supplierRepository.save(supplier);

        return supplierMapper.supplierToSupplierResponseDto(savedSupplier);
    }

    public SupplierResponseDto updateSupplierAddress(UUID id, UpdateAddressDTO updateAddressDTO) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
        Address newAddress = addressRepository.findById(updateAddressDTO.getAddressId()).orElseThrow(() -> new RuntimeException("Address not found with id: " + updateAddressDTO.getAddressId()));
        supplier.setAddress(newAddress);
        Supplier updatedSupplier = supplierRepository.save(supplier);
        return supplierMapper.supplierToSupplierResponseDto(updatedSupplier);
    }

    public void deleteSupplier(UUID id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + id));
        supplierRepository.delete(supplier);
    }

    public List<SupplierResponseDto> getAllSuppliers(Optional<Integer> limit, Optional<Integer> offset) {
        int page = offset.orElse(0);
        int pageSize = limit.orElse(10);

        if (limit.isPresent() || offset.isPresent()) {
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<Supplier> supplierPage = supplierRepository.findAll(pageable);
            return supplierMapper.toDtoList(supplierPage.getContent());
        }
        else {
            return supplierMapper.toDtoList(supplierRepository.findAll());
        }
    }

    public SupplierResponseDto getSupplierById(UUID id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Supplier not found with id: " + id));
        return supplierMapper.supplierToSupplierResponseDto(supplier);
    }


}
