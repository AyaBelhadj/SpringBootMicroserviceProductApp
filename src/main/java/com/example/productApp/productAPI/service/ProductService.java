package com.example.productApp.productAPI.service;

import com.example.productApp.productAPI.model.Product;
import com.example.productApp.productAPI.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository ;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    @Transactional
    public void addNewProduct(Product product){
        productRepository.save(product);
    }

    @Transactional
    public boolean updateProduct(String id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            productRepository.save(existingProduct);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean deleteProduct(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
            return true;
        } else {
            return false;
        }
    }

}
