package com.example.ShopAPI.services;

import com.example.ShopAPI.DTOs.mappers.ImagesMapper;
import com.example.ShopAPI.repositories.ImagesRepository;
import org.springframework.stereotype.Service;

@Service
public class ImagesService {
    private final ImagesRepository imagesRepository;
    private final ImagesMapper imagesMapper;

    public ImagesService(ImagesRepository imagesRepository, ImagesMapper imagesMapper) {
        this.imagesRepository = imagesRepository;
        this.imagesMapper = imagesMapper;
    }
}
