package com.example.ShopAPI.mappers;
import com.example.ShopAPI.DTOs.ProductRequestDto;
import com.example.ShopAPI.DTOs.ProductResponseDto;
import com.example.ShopAPI.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDto productToProductResponseDto(Product product);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    Product productRequestDtoToProduct(ProductRequestDto productDTO);
}
