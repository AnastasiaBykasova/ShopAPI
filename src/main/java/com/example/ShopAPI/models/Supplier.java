package com.example.ShopAPI.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Supplier")
public class Supplier {
    @Id
    @Column(name = "id", columnDefinition = "uuid default uuid_generate_v4()")
    private UUID id;
    @Column(name = "supplier_name", nullable = false)
    private String supplierName;
    @Column(name = "address_id", nullable = false)
    private UUID addressId;
    @Column(name = "phone_number", nullable = false, length = 30)
    private String phoneNumber;
}
