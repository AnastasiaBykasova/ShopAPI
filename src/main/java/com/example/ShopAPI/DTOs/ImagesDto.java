package com.example.ShopAPI.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ImagesDto {
    private UUID id;
    @NotNull
    private byte[] image;
}
