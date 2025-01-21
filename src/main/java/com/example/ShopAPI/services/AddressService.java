package com.example.ShopAPI.services;

import com.example.ShopAPI.mappers.AddressMapper;
import com.example.ShopAPI.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
}
