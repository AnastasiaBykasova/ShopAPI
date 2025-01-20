package com.example.ShopAPI.DTOs;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ClientResponseDto {
    private UUID id;
    private String clientName;
    private String clientSurname;
    private LocalDate birthday;
    private String gender;
    private LocalDate registrationDate;
    private UUID addressId;
}
