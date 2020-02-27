package br.com.github.gabrielotsuka.storesystem.services;

import br.com.github.gabrielotsuka.storesystem.controllers.request.item.ItemRequest;
import br.com.github.gabrielotsuka.storesystem.controllers.response.OrderResponse;
import br.com.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import br.com.github.gabrielotsuka.storesystem.models.Customer;
import br.com.github.gabrielotsuka.storesystem.models.Item;
import br.com.github.gabrielotsuka.storesystem.models.Order;
import br.com.github.gabrielotsuka.storesystem.models.Product;
import br.com.github.gabrielotsuka.storesystem.repositories.CustomerRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private OrderService orderService;
    @Mock
    private ProductService productService;
    @Mock
    private ItemService itemService;

    private Customer customer;
    @Before
    public void setup(){
        customer = new Customer("Gabriel", "gabriel@gmail.com", "12345");
        customer.setId(1L);
    }

//    Save Customer
    @Test
    public void saveCustomer_success(){
        customerService.saveCustomer(customer);
        verify(customerRepository, times(1)).save(any());
    }

//    Get Customers
    @Test
    public void getCustomers_success(){
        customerService.getCustomers();
        verify(customerRepository, times(1)).findAll();
    }

//    Get Stock
    @Test
    public void getStock_success(){
        customerService.getStock();
        verify(productService, times(1)).getProducts();
    }

//    Get Customer By Id
    @Test(expected = ResourceNotFoundException.class)
    public void getCustomerById_customerDoesNotExist(){
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customerService.getCustomerById(2L);
    }

    @Test
    public void getCustomerById_success(){
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Customer response = customerService.getCustomerById(1L);
        assertEquals("Gabriel", response.getName());
    }

//    Edit Customer
    @Test(expected = ResourceNotFoundException.class)
    public void editCustomer_customerDoesNotExist(){
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customerService.getCustomerById(2L);
    }

    @Test
    public void editCustomer_successWithoutPwd(){
        Customer request = new Customer("Otsuka", "gabriel@gmail.com");
        request.setId(2L);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Customer response = customerService.editCustomer(1L, request);
        assertEquals("Otsuka", response.getName());
        assertEquals("gabriel@gmail.com", response.getEmail());
        assertEquals("12345", response.getPwd());
    }


    @Test //Should not change password here
    public void editCustomer_successWithPwd(){
        Customer request = new Customer("Otsuka", "gabriel@gmail.com", "abcde");
        request.setId(2L);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Customer response = customerService.editCustomer(1L, request);
        assertEquals("Otsuka", response.getName());
        assertEquals("gabriel@gmail.com", response.getEmail());
        assertEquals("12345", response.getPwd());
    }

//      ChangeCustomerPwd
    @Test(expected = ResourceNotFoundException.class)
    public void  changeCustomerPwd_CustomerDoesNotExist(){
        Customer request = new Customer("abcde");
        request.setId(2L);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customerService.changeCustomerPwd(2L, request);
    }

    @Test
    public void  changeCustomerPwd_success(){
        Customer request = new Customer("abcde");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Customer response = customerService.changeCustomerPwd(1L, request);
        assertEquals("abcde", request.getPwd());
    }

//    Delete Customer
    @Test(expected = ResourceNotFoundException.class)
    public void deleteCustomer_customerDoesNotExist(){
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customerService.deleteCustomer(2L);
    }

    @Test
    public void deleteCustomer_success(){
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Customer response = customerService.deleteCustomer(1L);
        assertEquals("INVALID", response.getPwd());
        assertTrue(response.getEmail().contains("INVALID_"));
        verify(orderService, times(1)).closeOrder(any());
    }

//    Get Customer Orders
    @Test(expected = ResourceNotFoundException.class)
    public void getCustomerOrders_customerDoesNotExist(){
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customerService.getCustomerOrders(2L);
    }

    @Test
    public void getCustomerOrders_success(){
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customerService.getCustomerOrders(1L);
        verify(orderService, times(1)).getCustomerOrders(anyLong());
    }

//    Add Item To Order
    @Test(expected = ResourceNotFoundException.class)
    public void addItemToOrder_customerDoesNotExist(){
        Product prod = new Product();
        Item request = new Item();
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customerService.addItemToOrder(2L, request);
    }

    @Test
    public void addItemToOrder_success(){
        Product prod = new Product("pão", 2.5D, 5);
        Item request = new Item(prod, 2);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customerService.addItemToOrder(1L, request);
        verify(orderService, times(1)).addItemToOrder(any(), any());
    }

//    Set Item
    @Test
    public void setItem_success(){
        Product prod = new Product("pão", 2.5D, 5);
        ItemRequest request = new ItemRequest(1L, 3);
        when(productService.getProductById(any())).thenReturn(prod);
        Item response = customerService.setItem(request);
        assertEquals(7.5D, response.getItemPrice(), 0.1);
    }

//    Get Items By Order
    @Test
    public void getItemsByOrder(){
        Order order = new Order();
        customerService.getItemsByOrder(order);
        verify(itemService, times(1)).getItemsByOrder(any());
    }

//    Get Items By List Order
    @Test
    public void getItemsByListOrder(){
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        Order order2 = new Order();
        orders.add(order1);
        orders.add(order2);
        customerService.getItemsByListOrder(orders);
        verify(itemService, times(2)).getItemsByOrder(any());
    }

//    Remove Item From Order
    @Test(expected = ResourceNotFoundException.class)
    public void removeItemFromOrder_CustomerDoesNotExist(){
        Item request = new Item();
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customerService.removeItemFromOrder(2L, 1L);
    }

    @Test
    public void removeItemFromOrder_success(){
        Item request = new Item();
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customerService.removeItemFromOrder(1L, 1L);
        verify(orderService, times(1)).removeItemFromOrder(any(), any());
    }

//    Edit Item
    @Test(expected = ResourceNotFoundException.class)
    public void editItem_CustomerDoesNotExist(){
        Item request = new Item();
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customerService.editItem(2L, 1L, request);
    }

    @Test
    public void editItem_success(){
        Item request = new Item();
        Order orderR = new Order();
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(orderService.editItem(any(),any(),any())).thenReturn(orderR);
        customerService.editItem(1L, 1L, request);
        verify(orderService, times(1)).editItem(any(), any(), any());
    }

//    Clean Opened Order
    @Test(expected = ResourceNotFoundException.class)
    public void cleanOpenedOrder_CustomerDoesNotExist(){
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customerService.cleanOpenedOrder(2L);
    }

    @Test
    public void cleanOpenedOrder_success(){
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customerService.cleanOpenedOrder(1L);
        verify(orderService, times(1)).cleanOpenedOrder(any());
    }

//    Buy Order
    @Test(expected = ResourceNotFoundException.class)
    public void buyOrder_CustomerDoesNotExist(){
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customerService.buyOrder(2L);
    }

    @Test
    public void buyOrder_success(){
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customerService.buyOrder(1L);
        verify(orderService, times(1)).closeOrder(any());
    }


}
