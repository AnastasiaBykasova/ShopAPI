package com.example.ShopAPI.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Images")
public class Images {
    @Id
    @Column(name = "id", columnDefinition = "uuid default uuid_generate_v4()")
    private UUID id;
    @Column(name = "image", nullable = false, columnDefinition = "bytea")
    private byte[] image;
}
