package com.br.github.gabrielotsuka.storesystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

    @RequestMapping("/admin/products/register")
    public String registerProducts(){
        return "admin/products/register";
    }
}