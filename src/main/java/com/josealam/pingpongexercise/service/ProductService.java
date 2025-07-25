package com.josealam.pingpongexercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josealam.pingpongexercise.models.Product;
import com.josealam.pingpongexercise.models.ProductDTO;
import com.josealam.pingpongexercise.repository.CartRepository;
import com.josealam.pingpongexercise.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    


    public Product saveProduct(ProductDTO productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductDTO productDto) {
        var product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        product.get().setName(productDto.getName());
        product.get().setDescription(productDto.getDescription());
        product.get().setPrice(productDto.getPrice());
        return productRepository.save(product.get());
    }

    public Product getProductById(Long id) {
        var product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new RuntimeException("Product not found");
            
        }
        return product.get();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public String deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
        return "Product deleted successfully";
    }

}
