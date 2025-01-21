package com.example.ShopAPI.mappers;
import com.example.ShopAPI.DTOs.ImageDto;
import com.example.ShopAPI.models.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageDto imageToImageDto(Image image);
    Image imageDtoToImage(ImageDto imageDto);
}
