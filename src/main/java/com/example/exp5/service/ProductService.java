package com.example.exp5.service;

import com.example.exp5.entity.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    Product toggleAvailability(Long id);
    Product restock(Long id, Integer quantity);
}
