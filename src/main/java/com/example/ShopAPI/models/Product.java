package com.example.ShopAPI.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "category", nullable = false)
    private String category;
    @Column(name = "price", nullable = false, columnDefinition = "numeric")
    private BigDecimal price;
    @Column(name = "available_stock", nullable = false)
    private int availableStock;
    @Column(name = "last_update_date", nullable = false)
    private LocalDate lastUpdateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Supplier supplier;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;
    @Column(name = "supplier_id", nullable = false)
    private UUID supplierId;
//    @Column(name = "image_id")
//    private UUID imageId;

}
