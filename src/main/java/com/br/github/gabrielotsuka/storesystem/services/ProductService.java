package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import com.br.github.gabrielotsuka.storesystem.models.Product;
import com.br.github.gabrielotsuka.storesystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private Product verifyProductExistence(Long id) {
        Optional<Product> prod = productRepository.findById(id);
        if (!prod.isPresent())
            throw new ResourceNotFoundException("Product not found. ID: " + id);
        else
            return prod.get();
    }

    public Product saveProduct(Product product){
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

    public Product leaveItem(Long id, Integer quantity){
        Product oldProd = verifyProductExistence(id);
        oldProd.setQuantity(oldProd.getQuantity() - quantity);
        productRepository.save(oldProd);
        return oldProd;
    }

    public Product returnItem(Long id, Integer quantity){
        Product oldProd = verifyProductExistence(id);
        oldProd.setQuantity(oldProd.getQuantity() + quantity);
        productRepository.save(oldProd);
        return oldProd;
    }

    public void deleteProduct(Long id) {
        Product prod = verifyProductExistence(id);
        productRepository.delete(prod);
    }
}
