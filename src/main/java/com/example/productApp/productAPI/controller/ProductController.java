package com.example.productApp.productAPI.controller;

import com.example.productApp.productAPI.model.Product;
import com.example.productApp.productAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="api/v1/product")
public class ProductController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    ProductService productService;
    @GetMapping("/health")
    public String healthCheck() {
        try {
            // Execute a simple query to check database connectivity
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return "Database is reachable";
        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }
    @GetMapping
    public List<Product> getProducts()
    {return productService.getProducts(); }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok().body(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/products/save")
    public void addProduct(@RequestBody Product product){
        product.setId(UUID.randomUUID().toString());
        productService.addNewProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        boolean updated = productService.updateProduct(id, product);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}