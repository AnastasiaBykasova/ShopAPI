package com.example.ShopAPI.models;

public enum Gender {
    Male("Мужской"),
    Female("Женский");
    private final String genderValue;
    Gender(String genderValue) {
        this.genderValue = genderValue;
    }
}
