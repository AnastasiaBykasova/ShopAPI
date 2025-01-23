package com.example.ShopAPI.DTOs;

import com.example.ShopAPI.models.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for supplier creation request")
public class SupplierRequestDto {
    @NotBlank
    @Schema(description = "Name of the supplier", example = "Doris")
    private String supplierName;
    @NotNull
    @Schema(description = "Address id of the supplier", example = "123e4567-e89b-12d3-a456-556642440000")
    private UUID addressId;
    @NotBlank
    @Schema(description = "Phone number of the supplier", example = "+7(999)999-99-99")
    private String phoneNumber;
}
