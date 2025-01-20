package com.example.ShopAPI.mappers;
import com.example.ShopAPI.DTOs.ImagesDto;
import com.example.ShopAPI.models.Images;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagesMapper {
    ImagesDto imagesToImagesDto(Images images);
    Images imagesDtoToImages(ImagesDto imagesDto);
}
