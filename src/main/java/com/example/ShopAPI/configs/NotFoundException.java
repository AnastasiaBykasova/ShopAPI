package com.example.ShopAPI.configs;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}