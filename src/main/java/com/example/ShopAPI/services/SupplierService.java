package com.example.ShopAPI.services;

import com.example.ShopAPI.DTOs.mappers.SupplierMapper;
import com.example.ShopAPI.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierService(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }
}
