package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import com.br.github.gabrielotsuka.storesystem.models.Product;
import com.br.github.gabrielotsuka.storesystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminProductService {
    private final ProductRepository productRepository;
    @Autowired
    public AdminProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product){
        productRepository.save(product);
        return product;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return verifyProductExistence(id);
    }

    public Product editProduct(Product newProd, Long id) {
        Product oldProd = verifyProductExistence(id);
        oldProd.setName(newProd.getName());
        oldProd.setPrice(newProd.getPrice());
        oldProd.setQuantity(newProd.getQuantity());
        productRepository.save(oldProd);
        return oldProd;
    }

    public void deleteProduct(Long id) {
        Product prod = verifyProductExistence(id);
        productRepository.delete(prod);
    }

    private Product verifyProductExistence(Long id) {
        Optional<Product> prod = productRepository.findById(id);
        if (!prod.isPresent())
            throw new ResourceNotFoundException("Product not found. ID: " + id);
        else
            return prod.get();
    }
}
