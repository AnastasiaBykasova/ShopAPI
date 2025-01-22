package com.example.ShopAPI.models;

import com.example.ShopAPI.helpers.CheckAddress;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Supplier")
public class Supplier implements CheckAddress {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "supplier_name", nullable = false)
    private String supplierName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @Column(name = "phone_number", nullable = false, length = 30)
    private String phoneNumber;

    @Override
    public UUID getAddressId() {
        return address != null ? address.getId() : null;
    }
}
