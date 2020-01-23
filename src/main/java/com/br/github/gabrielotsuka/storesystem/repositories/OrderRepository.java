package com.br.github.gabrielotsuka.storesystem.repositories;

import com.br.github.gabrielotsuka.storesystem.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
