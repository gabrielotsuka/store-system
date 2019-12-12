package com.br.github.gabrielotsuka.storesystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

    @RequestMapping("/ProductRegister")
    public String form(){
        return "product/formProduct";
    }
}
