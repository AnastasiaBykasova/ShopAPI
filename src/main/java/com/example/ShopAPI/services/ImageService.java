package com.example.ShopAPI.services;

import com.example.ShopAPI.mappers.ImageMapper;
import com.example.ShopAPI.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

}
