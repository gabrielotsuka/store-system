package com.br.github.gabrielotsuka.storesystem.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Client {

    private String name;
    private String CPF;
    private Map<Product,Integer> dict = new HashMap<>();

    public Client(String name, String CPF){
        this.name = name;
        this.CPF = CPF;
    }

    public void addToCart(Product p, Integer i){
        this.dict.put(p,i);
    }

    public void cleanCart(){
        this.dict.clear();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public double getTotalPrice(){
        Set<Product> keys = this.dict.keySet();
        double total = 0.0;
        for(Product key : keys){
            total += this.dict.get(key);
        }
        return total;
    }
}
