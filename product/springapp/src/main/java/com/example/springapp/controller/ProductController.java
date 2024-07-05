package com.example.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapp.model.Product;
import com.example.springapp.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService apiService;

      @PostMapping("/product")
    public ResponseEntity<Product> addproductDetails(@RequestBody Product product) {
        Product added = apiService.addProductDetails(product);
        if (added != null) {
            return new ResponseEntity<>(added,HttpStatus.CREATED);
        } else {
            return new  ResponseEntity<>(added , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/product/{productId}/{quantityInStock}")
    public ResponseEntity<Product> updateQuantityInStock(
            @PathVariable int productId,
            @PathVariable int quantityInStock) {

        try {
            Product updatedProduct = apiService.updateQuantityInStockForProduct(productId, quantityInStock);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
    
        try {
            apiService.deleteproductId(productId);
            return ResponseEntity.ok("Product deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting product.");
        }
    }

     @GetMapping("/product/bycategory/{category}")
    public List<Product> getProductByCategory(@PathVariable String category) {
        return apiService.getProductByCategory(category);
    }

}
