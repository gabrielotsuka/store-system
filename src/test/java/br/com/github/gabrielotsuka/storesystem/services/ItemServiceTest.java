package br.com.github.gabrielotsuka.storesystem.services;

import br.com.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import br.com.github.gabrielotsuka.storesystem.models.Item;
import br.com.github.gabrielotsuka.storesystem.models.Order;
import br.com.github.gabrielotsuka.storesystem.models.Product;
import br.com.github.gabrielotsuka.storesystem.repositories.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ItemServiceTest {
    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private ProductService productService;

    private Order order;
    private Item item;
    private Product product;
    @Before
    public void setup(){
        order = new Order();
        product = new Product("Batata", 1.5, 100);
        item = new Item(product, 2, 3);
    }

//    addItemToOrder
    @Test
    public void addItemToOrder_success(){
        Item response = itemService.addItemToOrder(order, item);
        assertEquals(order, response.getOrder());
        verify(itemRepository, times(1)).save(item);
        verify(productService, times(1)).leaveItem(any(), any());
    }

//    Get Items By Order
    @Test
    public void getItemsByOrder_success(){
        itemService.getItemsByOrder(order);
        verify(itemRepository, times(1)).findByOrderId(order.getId());
    }

//    Get Item By id
    @Test
    public void getItemById_success(){
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        Item response = itemService.getItemById(1L);
        assertEquals(new Integer(2), response.getQuantity());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getItemById_itemDoesNotExist(){
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        Item response = itemService.getItemById(2L);
    }

//    Remove Item
    @Test
    public void removeItem_success(){
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        itemService.removeItem(1L);
        verify(itemRepository, times(1)).delete(item);
        verify(productService, times(1)).returnItem(any(), any());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void removeItem_itemDoesNotExist(){
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        Item response = itemService.removeItem(2L);
    }

//    Edit Item
    @Test
    public void editItem_success(){
        Product newProd = new Product("Banana", 1.2, 50);
        Item request = new Item(newProd, 3, 3.6);
        Item response = itemService.editItem(item, request);
        verify(productService, times(1)).returnItem(any(), any());
        assertEquals(new Integer(3), response.getQuantity());
        assertEquals(newProd, response.getProduct());
        assertEquals(3.6, response.getItemPrice(), 0.1);
        verify(productService, times(1)).leaveItem(any(), any());
        verify(itemRepository, times(1)).save(any());
    }

//    Remove All Items
    @Test
    public void removeAllItems_success(){
        itemService.removeAllItems(order);
        verify(itemRepository, times(1)).findByOrderId(any());
    }
}
