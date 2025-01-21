package com.example.ShopAPI.repositories;

import com.example.ShopAPI.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    List<Client> findByClientNameAndClientSurname(String name, String surname);
    Page<Client> findAll(Pageable pageable);
}
