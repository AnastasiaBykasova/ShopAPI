package com.example.ShopAPI.models;

import com.example.ShopAPI.helpers.CheckAddress;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table (name = "Client")
public class Client implements CheckAddress {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "client_name", nullable = false)
    private String clientName;
    @Column(name = "client_surname", nullable = false)
    private String clientSurname;
    @Column(name = "birthday", columnDefinition = "date")
    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "gender_type")
    private Gender gender;
    @Column(name = "registration_date", nullable = false, columnDefinition = "date")
    private LocalDate registrationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Override
    public UUID getAddressId() {
        return address != null ? address.getId() : null;
    }
}
