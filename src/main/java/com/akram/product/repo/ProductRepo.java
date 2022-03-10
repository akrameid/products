package com.akram.product.repo;

import com.akram.product.model.Customer;
import com.akram.product.model.Order;
import com.akram.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
