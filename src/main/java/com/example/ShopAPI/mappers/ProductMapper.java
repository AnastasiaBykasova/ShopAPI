package com.example.ShopAPI.mappers;
import com.example.ShopAPI.DTOs.ProductDto;
import com.example.ShopAPI.models.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto productDTO);
}
