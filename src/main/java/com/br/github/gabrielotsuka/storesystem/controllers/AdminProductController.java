package com.br.github.gabrielotsuka.storesystem.controllers;

import com.br.github.gabrielotsuka.storesystem.controllers.request.product.ProductSavingRequest;
import com.br.github.gabrielotsuka.storesystem.models.Product;
import com.br.github.gabrielotsuka.storesystem.services.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/product")
public class AdminProductController {
    private final AdminProductService adminProductService;
    @Autowired
    public AdminProductController(AdminProductService adminProductService) {
        this.adminProductService = adminProductService;
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody @Valid ProductSavingRequest product){
        return new ResponseEntity<Product>(adminProductService.save(product.toProduct()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<List<Product>>(adminProductService.getProducts(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<Product>(adminProductService.getProductById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> editProduct(@RequestBody @Valid ProductSavingRequest newProd,
                                               @PathVariable(value = "id") Long id){
        return new ResponseEntity<Product>(adminProductService.editProduct(newProd.toProduct(), id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Long id){
        adminProductService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
