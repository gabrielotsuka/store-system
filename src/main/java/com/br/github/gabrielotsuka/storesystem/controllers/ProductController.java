package com.br.github.gabrielotsuka.storesystem.controllers;

import com.br.github.gabrielotsuka.storesystem.controllers.request.ProductRequest;
import com.br.github.gabrielotsuka.storesystem.models.Product;
import com.br.github.gabrielotsuka.storesystem.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.OverridesAttribute;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/product")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody @Valid ProductRequest product){
        return productService.save(product.toProduct());
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long id){
        return productService.getProductById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> editProduct(@RequestBody @Valid ProductRequest newProd,
                                               @PathVariable(value = "id") Long id){
        return productService.editProduct(newProd.toProduct(), id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Long id){
        return productService.deleteProduct(id);
    }

}
