package com.example.ShopAPI.services;

import com.example.ShopAPI.DTOs.ProductRequestDto;
import com.example.ShopAPI.DTOs.ProductResponseDto;
import com.example.ShopAPI.DTOs.ProductStockUpdateRequestDto;
import com.example.ShopAPI.mappers.ProductMapper;
import com.example.ShopAPI.models.Product;
import com.example.ShopAPI.models.Supplier;
import com.example.ShopAPI.repositories.ImageRepository;
import com.example.ShopAPI.repositories.ProductRepository;
import com.example.ShopAPI.repositories.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final ProductMapper productMapper;

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = productMapper.productRequestDtoToProduct(productRequestDto);
        Supplier supplier = supplierRepository.findById(productRequestDto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + productRequestDto.getSupplierId()));
        product.setSupplierId(supplier.getId());

        if (productRequestDto.getImageId() != null) {
            product.setImageId(productRequestDto.getImageId());
        }

        Product savedProduct = productRepository.save(product);
        return productMapper.productToProductResponseDto(savedProduct);
    }

   public ProductResponseDto reduceProductStock(UUID id, ProductStockUpdateRequestDto updateRequestDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        int current = product.getAvailableStock();
        int toReduce = updateRequestDto.getToReduce();
        if (current < toReduce) {
            throw new IllegalArgumentException("Cannot reduce stock by " + toReduce + ". Current stock is " + current);
        }
        product.setAvailableStock(current - toReduce);
        Product updatedProduct = productRepository.save(product);
        return productMapper.productToProductResponseDto(updatedProduct);
   }

    public ProductResponseDto getProductById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        return productMapper.productToProductResponseDto(product);
    }

    public List<ProductResponseDto> getAllProducts(Optional<Integer> limit, Optional<Integer> offset) {
        int page = offset.orElse(0);
        int pageSize = limit.orElse(10);

        if (limit.isPresent() || offset.isPresent()) {
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<Product> productPage = productRepository.findAll(pageable);
            return productMapper.toDtoList(productPage.getContent());
        }
        else {
            return productMapper.toDtoList(productRepository.findAll());
        }
    }

    public void deleteProduct(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }

}
