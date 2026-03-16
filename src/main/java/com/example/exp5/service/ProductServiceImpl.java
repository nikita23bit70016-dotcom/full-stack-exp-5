package com.example.exp5.service;

import com.example.exp5.entity.Product;
import com.example.exp5.exception.ResourceNotFoundException;
import com.example.exp5.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    @Override
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existing = getProductById(id);
        existing.setName(product.getName());
        existing.setCategory(product.getCategory());
        existing.setPrice(product.getPrice());
        existing.setStockQuantity(product.getStockQuantity());
        existing.setDescription(product.getDescription());
        return repository.save(existing);
    }

    @Override
    public void deleteProduct(Long id) {
        Product existing = getProductById(id);
        repository.delete(existing);
    }

    @Override
    public Product toggleAvailability(Long id) {
        Product product = getProductById(id);
        product.setStatus("Available".equals(product.getStatus()) ? "Out of Stock" : "Available");
        return repository.save(product);
    }

    @Override
    public Product restock(Long id, Integer quantity) {
        Product product = getProductById(id);
        product.setStockQuantity(product.getStockQuantity() + quantity);
        if (product.getStockQuantity() > 0) {
            product.setStatus("Available");
        }
        return repository.save(product);
    }
}
