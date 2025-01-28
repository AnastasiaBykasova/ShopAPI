package com.example.ShopAPI.mappers;
import com.example.ShopAPI.DTOs.ImageRequestDto;
import com.example.ShopAPI.models.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageRequestDto imageToImageDto(Image image);

    @Mapping(target = "id", ignore = true)
    Image imageDtoToImage(ImageRequestDto imageDto);
}
