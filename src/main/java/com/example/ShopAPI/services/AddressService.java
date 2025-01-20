package com.example.ShopAPI.services;

import com.example.ShopAPI.mappers.AddressMapper;
import com.example.ShopAPI.repositories.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }
}
