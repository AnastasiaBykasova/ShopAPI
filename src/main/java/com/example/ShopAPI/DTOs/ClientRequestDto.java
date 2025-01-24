package com.example.ShopAPI.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for client creation request")
public class ClientRequestDto {
    @NotBlank
    @Schema(description = "Name of the client", example = "Анна")
    private String clientName;
    @NotBlank
    @Schema(description = "Surname of the client", example = "Кротова")
    private String clientSurname;
    @NotNull
    @Schema(description = "Birthday of the client", example = "2001-10-10")
    private LocalDate birthday;
    @NotBlank
    @Schema(description = "Gender of the client", example = "Female")
    private String gender;
    @NotNull
    @Schema(description = "Registration date of the client", example = "2025-01-21")
    private LocalDate registrationDate;
    @NotNull
    @Schema(description = "Address id of the client", example = "123e4567-e89b-12d3-a456-556642440000")
    private UUID addressId;
}
