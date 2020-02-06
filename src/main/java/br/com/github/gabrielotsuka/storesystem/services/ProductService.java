package br.com.github.gabrielotsuka.storesystem.services;

import br.com.github.gabrielotsuka.storesystem.error.NotEnoughProductsException;
import br.com.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import br.com.github.gabrielotsuka.storesystem.models.Product;
import br.com.github.gabrielotsuka.storesystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
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

    @Transactional
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return verifyProductExistence(id);
    }

    @Transactional
    public Product editProduct(Product newProd, Long id) {
        Product oldProd = verifyProductExistence(id);
        oldProd.setName(newProd.getName());
        oldProd.setPrice(newProd.getPrice());
        oldProd.setQuantity(newProd.getQuantity());
        productRepository.save(oldProd);
        return oldProd;
    }

    @Transactional
    public Product leaveItem(Long id, Integer quantity){
        Product oldProd = verifyProductExistence(id);
        int finalQtt = oldProd.getQuantity() - quantity;
        if (finalQtt < 0)
            throw new NotEnoughProductsException("This quantity of product is not available. Product ID: " + id);
        oldProd.setQuantity(finalQtt);
        productRepository.save(oldProd);
        return oldProd;
    }

    @Transactional
    public Product returnItem(Long id, Integer quantity){
        Product oldProd = verifyProductExistence(id);
        oldProd.setQuantity(oldProd.getQuantity() + quantity);
        productRepository.save(oldProd);
        return oldProd;
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product prod = verifyProductExistence(id);
        productRepository.delete(prod);
    }

    @Transactional
    public Product changeProductQuantity(Long id, Product request) {
        Product product = verifyProductExistence(id);
        product.setQuantity(product.getQuantity() + request.getQuantity());
        productRepository.save(product);
        return product;
    }

    public Product changeProductPrice(Long id, Product request) {
        Product product = verifyProductExistence(id);
        product.setPrice(request.getPrice());
        productRepository.save(product);
        return product;
    }
}
