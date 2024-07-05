package com.example.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springapp.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Modifying
    @Query("UPDATE Product p SET p.quantityInStock = :quantityInStock WHERE p.productId = :productId")
    void updateQuantityInStockForProduct(int productId, int quantityInStock);

    @Modifying
    @Query("DELETE FROM Product p WHERE p.productId = :productId")
    void deleteProductByproductId(int productId);

    @Query("SELECT p FROM Product  p WHERE p.category = :category")
    List<Product> findByprice(String category);
    
}
