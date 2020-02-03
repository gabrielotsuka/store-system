package br.com.github.gabrielotsuka.storesystem.repositories;

import br.com.github.gabrielotsuka.storesystem.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
