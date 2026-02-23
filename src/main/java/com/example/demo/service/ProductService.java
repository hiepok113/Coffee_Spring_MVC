package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @CacheEvict(value = "products_cache", allEntries = true)
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    @Cacheable(value = "products_cache")
    public List<Product> getAllProducts() {
        System.out.println("====== FETCHING PRODUCTS FROM MYSQL DATABASE ======");
        return productRepository.findAll();
    }

    public Page<Product> getProductsWithPagination(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    
    @CacheEvict(value = "products_cache", allEntries = true)
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}