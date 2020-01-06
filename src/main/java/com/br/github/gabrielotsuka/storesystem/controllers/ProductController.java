package com.br.github.gabrielotsuka.storesystem.controllers;

import com.br.github.gabrielotsuka.storesystem.models.Employee;
import com.br.github.gabrielotsuka.storesystem.models.Product;
import com.br.github.gabrielotsuka.storesystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/admin/products/register")
    public ModelAndView register(Product product){
        ModelAndView mv = new ModelAndView(("admin/products/register"));
        mv.addObject("product", product);
        return mv;
    }

    @PostMapping("admin/products/save")
    public ModelAndView save(@Valid Product product, BindingResult result){
        if(result.hasErrors()){
            return register(product);
        }
        productRepository.saveAndFlush(product);
        return register(new Product());
    }

}