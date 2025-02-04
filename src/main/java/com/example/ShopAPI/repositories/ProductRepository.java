package com.example.ShopAPI.repositories;

import com.example.ShopAPI.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByImageId(UUID imageId);
    List<Product> findAllBySupplierId(UUID supplierId);
}