package com.br.github.gabrielotsuka.storesystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrimaryController {

    @RequestMapping("/admin")
    public String accessPrimary(){
        return "admin/home";
    }
}
