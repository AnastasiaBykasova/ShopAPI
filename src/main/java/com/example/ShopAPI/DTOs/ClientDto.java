package com.example.ShopAPI.DTOs;

import com.example.ShopAPI.models.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ClientDto {
    private UUID id;
    @NotBlank
    private String clientName;
    @NotBlank
    private String clientSurname;
    private LocalDate birthday;

    private Gender gender;
//    @NotBlank
//    private String gender;

    @NotNull
    private LocalDate registrationDate;
    @NotNull
    private UUID addressId;

}