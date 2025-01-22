package com.example.ShopAPI.services;

import com.example.ShopAPI.DTOs.ProductRequestDto;
import com.example.ShopAPI.DTOs.ProductResponseDto;
import com.example.ShopAPI.mappers.ProductMapper;
import com.example.ShopAPI.models.Image;
import com.example.ShopAPI.models.Product;
import com.example.ShopAPI.models.Supplier;
import com.example.ShopAPI.repositories.ImageRepository;
import com.example.ShopAPI.repositories.ProductRepository;
import com.example.ShopAPI.repositories.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final ImageRepository imageRepository;
    private final ProductMapper productMapper;

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = productMapper.productRequestDtoToProduct(productRequestDto);
        Supplier supplier = supplierRepository.findById(productRequestDto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + productRequestDto.getSupplierId()));
        product.setSupplierId(supplier.getId());

        if (productRequestDto.getImageId() != null) {
            product.setImageId(productRequestDto.getImageId());
        }

//        Image image = imageRepository.findById(productRequestDto.getImageId())
//                .orElseThrow(() -> new RuntimeException("Image not found with id: " + productRequestDto.getImageId()));
//        product.setImageId(image.getId());

        Product savedProduct = productRepository.save(product);
        return productMapper.productToProductResponseDto(savedProduct);
    }

}
