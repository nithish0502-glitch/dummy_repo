package com.example.springapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.model.Product;
import com.example.springapp.repository.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo proRepo;

    public Product addProductDetails(Product product){
        try{
            return proRepo.save(product);
        }catch(Exception e){
            return null;
    
    }
    
}
    @Transactional
    public Product updateQuantityInStockForProduct(int productId, int quantityInStock) {
        proRepo.updateQuantityInStockForProduct(productId, quantityInStock);
        return proRepo.findById(productId).orElse(null);
    }

    @Transactional
    public void deleteproductId(int productId) {
        proRepo.deleteProductByproductId(productId);
    }


    public List<Product> getProductByCategory(String  category) {
        try{
            return proRepo.findByprice(category);
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

}
