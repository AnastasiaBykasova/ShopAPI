package com.example.ShopAPI.services;

import com.example.ShopAPI.DTOs.ImageRequestDto;
import com.example.ShopAPI.DTOs.ImageResponseDto;
import com.example.ShopAPI.DTOs.ImageUpdateRequestDto;
import com.example.ShopAPI.mappers.ImageMapper;
import com.example.ShopAPI.models.Image;
import com.example.ShopAPI.models.Product;
import com.example.ShopAPI.repositories.ImageRepository;
import com.example.ShopAPI.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    private final ProductRepository productRepository;

    @Transactional
    public ImageResponseDto addImageToProduct(ImageRequestDto imageDto) {
        Product product = productRepository.findById(imageDto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + imageDto.getProductId()));
        Image image = imageMapper.imageDtoToImage(imageDto);
        Image savedImage = imageRepository.save(image);
        product.setImage(savedImage);
        LocalDate date = LocalDate.now();
        product.setLastUpdateDate(date);
        productRepository.save(product);
        return new ImageResponseDto(savedImage.getId());
    }

    @Transactional
    public ImageResponseDto updateImage(UUID id, ImageUpdateRequestDto updateRequestDto) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Image not found with id: " + id));
        byte[] newImage;
        try {
            newImage = Base64.getDecoder().decode(updateRequestDto.getImage());
        }
        catch (IllegalArgumentException e) {
            throw new EntityNotFoundException("Invalid Base64 image string: " + e.getMessage());
        }
        image.setImage(newImage);
        Image updatedImage = imageRepository.save(image);
        return new ImageResponseDto(updatedImage.getId());
    }

    @Transactional
    public void deleteImage(UUID id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image not found with id: " + id));
        List<Product> productsWithImage = productRepository.findAllByImageId(id);
        for (Product product : productsWithImage) {
            product.setImage(null);
            productRepository.save(product);
        }
        imageRepository.delete(image);
    }

    public byte[] getProductImage(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
        Image image = product.getImage();
        if (image == null) {
            throw new EntityNotFoundException("Image not found for product with id: " + productId);
        }
        return image.getImage();
    }

    public byte[] getImageById(UUID id) {
        Optional<Image> optionalImage = imageRepository.findById(id);
        Image image = optionalImage.orElseThrow(() -> new EntityNotFoundException("Image not found with id: " + id));
        return image.getImage();
    }

    public boolean exists(UUID id){
        return imageRepository.existsById(id);
    }

}
