package com.br.github.gabrielotsuka.storesystem.repositories;

import com.br.github.gabrielotsuka.storesystem.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
