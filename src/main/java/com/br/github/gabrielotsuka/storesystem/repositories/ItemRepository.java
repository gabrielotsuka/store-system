package com.br.github.gabrielotsuka.storesystem.repositories;

import com.br.github.gabrielotsuka.storesystem.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
