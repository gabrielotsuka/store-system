package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.models.Item;
import com.br.github.gabrielotsuka.storesystem.models.Order;
import com.br.github.gabrielotsuka.storesystem.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item addItemToOrder(Order order, Item item) {
        item.setOrder(order);
        itemRepository.save(item);
        return item;
    }
}
