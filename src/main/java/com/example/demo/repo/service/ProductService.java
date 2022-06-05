package com.example.demo.repo.service;

import com.example.demo.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProductService {

    Product saveProduct(Product product);

    List<Product> findAll();

    Product findById(Long id);

    void deleteProduct(Product product);

}
