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
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<Product> save(Product product){
        productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public ResponseEntity<Product> getProductById(Long id) {
        return new ResponseEntity<Product>(verifyProductExistence(id), HttpStatus.OK);
    }

    public ResponseEntity<Product> editProduct(Product newProd, Long id) {
        Product oldProd = verifyProductExistence(id);
        oldProd.setName(newProd.getName());
        oldProd.setPrice(newProd.getPrice());
        oldProd.setQuantity(newProd.getQuantity());
        productRepository.save(oldProd);
        return new ResponseEntity<Product>(oldProd, HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteProduct(Long id) {
        Product prod = verifyProductExistence(id);
        productRepository.delete(prod);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Product verifyProductExistence(Long id) {
        Optional<Product> prod = productRepository.findById(id);
        if (!prod.isPresent())
            throw new ResourceNotFoundException("Product not found. ID: " + id);
        else
            return prod.get();
    }
}
