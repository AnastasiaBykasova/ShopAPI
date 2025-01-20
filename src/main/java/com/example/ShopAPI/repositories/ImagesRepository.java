package com.example.ShopAPI.repositories;

import com.example.ShopAPI.models.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImagesRepository extends JpaRepository<Images, UUID> {

}