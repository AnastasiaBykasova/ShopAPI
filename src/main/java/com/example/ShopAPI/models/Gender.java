package com.example.ShopAPI.models;

public enum Gender {
//    Мужской, Женский
    Male("Мужской"),
    Female("Женский");
    private final String genderValue;
    Gender(String genderValue) {
        this.genderValue = genderValue;
    }
}
