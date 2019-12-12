package com.br.github.gabrielotsuka.storesystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientController {

    @RequestMapping("/ClientRegister")
    public String formClient(){
        return "client/formClient";
    }

}
