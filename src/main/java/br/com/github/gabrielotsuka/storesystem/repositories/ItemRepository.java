package br.com.github.gabrielotsuka.storesystem.repositories;

import br.com.github.gabrielotsuka.storesystem.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    public List<Item> findByOrderId(Long order_id);
}
