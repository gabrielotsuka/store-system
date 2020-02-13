package br.com.github.gabrielotsuka.storesystem.services;

import br.com.github.gabrielotsuka.storesystem.error.ClosedOrderException;
import br.com.github.gabrielotsuka.storesystem.models.Customer;
import br.com.github.gabrielotsuka.storesystem.models.Item;
import br.com.github.gabrielotsuka.storesystem.models.Order;
import br.com.github.gabrielotsuka.storesystem.models.Product;
import br.com.github.gabrielotsuka.storesystem.repositories.OrderRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ItemService itemService;

    private Customer customer;
    private Product product;
    private Item item;
    private Order order;

    @Before
    public void setup(){
        customer = new Customer("Gabriel Otsuka", "gabrielotsuka@gmail.com", "abcd");
        customer.setId(1L);
        order = new Order();
        order.setCustomer(customer);
        order.setId(1L);
        product = new Product("batata", 0.5, 50);
        product.setId(1L);
        item = new Item(product, 10, 5);
        item.setOrder(order);
        item.setId(1L);
    }

//    Get Customer Orders
    @Test
    public void getCustomerOrders_success(){
        orderService.getCustomerOrders(1L);
        verify(orderRepository, times(1)).findByCustomerId(1L);
    }

//    Add Item To Order
    @Test
    public void addItemToOrder_successWithOpenedOrder(){
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        when(orderRepository.findByCustomerId(customer.getId())).thenReturn(orders);
        Order response = orderService.addItemToOrder(customer, item);
        verify(itemService, times(1)).addItemToOrder(any(), any());
        verify(orderRepository, times(1)).save(any());
        Assert.assertEquals(5d, response.getTotalPrice(), 0.1);
    }

    @Test
    public void addItemToOrder_successWithClosedOrder(){
        order.setStatus("Closed");
        Order response = orderService.addItemToOrder(customer, item);
        verify(itemService, times(1)).addItemToOrder(any(), any());
        verify(orderRepository, times(2)).save(any());
        Assert.assertEquals(5d, response.getTotalPrice(), 0.1);
    }

//    Remove Item From Order
    @Test
    public void removeItemFromOrder_successWithOpenedOrder(){
        order.setTotalPrice(10);
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        when(orderRepository.findByCustomerId(customer.getId())).thenReturn(orders);
        when(itemService.removeItem(1L)).thenReturn(item);
        Order response = orderService.removeItemFromOrder(item.getId(), customer);
        verify(itemService, times(1)).removeItem(any());
        verify(orderRepository, times(1)).save(any());
        Assert.assertEquals(5.0, response.getTotalPrice(), 0.1);
    }

    @Test
    public void removeItemFromOrder_successWithClosedOrder(){
        when(itemService.removeItem(1L)).thenReturn(item);
        Order response = orderService.removeItemFromOrder(item.getId(), customer);
        verify(itemService, times(1)).removeItem(any());
        verify(orderRepository, times(2)).save(any());
        Assert.assertEquals(-5.0, response.getTotalPrice(), 0.1);
    }

//    Edit Item
        @Test
    public void  editItem_success(){
        Product prod1 = new Product("pão", 0.2, 50);
        prod1.setId(2L);
        Item it1= new Item(prod1, 10, 2);
        it1.setOrder(order);
        it1.setId(2L);
        when(itemService.getItemById(any())).thenReturn(item);
        orderService.editItem(customer, 1L, it1);

    }

    @Test(expected = ClosedOrderException.class)
    public void editItem_closedOrder(){
        order.setStatus("Closed");
        when(itemService.getItemById(any())).thenReturn(item);
        orderService.editItem(customer, 1L, item);
        verify(itemService, times(1)).editItem(any(), any());
        verify(orderRepository, times(1)).save(any());
    }
}
