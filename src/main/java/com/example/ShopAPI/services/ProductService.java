package com.example.ShopAPI.services;

import com.example.ShopAPI.DTOs.mappers.ProductMapper;
import com.example.ShopAPI.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
}
