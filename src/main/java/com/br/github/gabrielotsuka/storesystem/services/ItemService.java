package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import com.br.github.gabrielotsuka.storesystem.models.Item;
import com.br.github.gabrielotsuka.storesystem.models.Order;
import com.br.github.gabrielotsuka.storesystem.repositories.ItemRepository;
import com.br.github.gabrielotsuka.storesystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private final ItemRepository itemRepository;
    @Autowired
    private final ProductService productService;

    public ItemService(ItemRepository itemRepository,
                       ProductRepository productRepository,
                       ProductService productService) {
        this.itemRepository = itemRepository;
        this.productService = productService;
    }

    public Item addItemToOrder(Order order, Item item) {
        item.setOrder(order);
        itemRepository.save(item);
        productService.leaveItem(item.getProduct().getId(), item.getQuantity());
        return item;
    }

    public List<Item> getItemsByOrder(Order order) {
        return itemRepository.findByOrderId(order.getId());
    }

    public Item verifyItemExistence(Long id){
        Optional<Item> item = itemRepository.findById(id);
        if(!item.isPresent())
            throw new ResourceNotFoundException("Item not found. ID: " + id);
        else
            return item.get();
    }

    public Order removeItem(Item item) {
        itemRepository.delete(item);
        productService.returnItem(item.getProduct().getId(), item.getQuantity());
        return item.getOrder();
    }
}
