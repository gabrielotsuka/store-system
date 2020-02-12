package br.com.github.gabrielotsuka.storesystem.services;

import br.com.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import br.com.github.gabrielotsuka.storesystem.models.Admin;
import br.com.github.gabrielotsuka.storesystem.models.Customer;
import br.com.github.gabrielotsuka.storesystem.models.Order;
import br.com.github.gabrielotsuka.storesystem.models.Product;
import br.com.github.gabrielotsuka.storesystem.repositories.AdminRepository;
import br.com.github.gabrielotsuka.storesystem.repositories.CustomerRepository;
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

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class AdminServiceTest {
    @InjectMocks
    private AdminService adminService;

    @Mock
    private AdminRepository adminRepository;
    @Mock
    private CustomerService customerService;
    @Mock
    private ProductService productService;

    private Admin admin;
    private Customer customer;
    private Product product;
    @Before
    public void setup(){
        adminService = new AdminService(customerService, adminRepository, productService);
        admin = new Admin("Gabriel Otsuka", "gabrielotsuka@gmail.com", "abcde");
        admin.setId(1L);
        customer = new Customer("Gabriel Otsuka", "gabrielotsuka@gmail.com", "abcde");
        customer.setId(1L);
        product = new Product("batata", 1.50, 50);
        product.setId(1L);
    }

//                                              Admin
//    Save Admin
    @Test
    public void saveAdmin_success(){
        adminService.saveAdmin(admin);
        verify(adminRepository, times(1)).save(admin);
    }

//    Get All Amins
    @Test
    public void getAdmins(){
        adminService.getAdmins();
        verify(adminRepository, times(1)).findAll();
    }

//    Get Admin By Id
    @Test
    public void getAdminById_success(){
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        Admin adminResponse = adminService.getAdminById(1L);
        Assert.assertEquals("gabrielotsuka@gmail.com", adminResponse.getEmail());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getAdminById_adminDoesNotExist(){
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        adminService.getAdminById(2L);
    }

//    Edit Admin
    @Test
    public void editAdmin_success(){
        Admin request = new Admin("Gabriel Zezin", "gabriel.otsuka@zup.com.br", "123");
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        Admin adminResponse = adminService.editAdmin(admin.getId(), request);
        Assert.assertEquals(Optional.of(1L), Optional.ofNullable(adminResponse.getId()));
        Assert.assertEquals("Gabriel Zezin", adminResponse.getName());
        Assert.assertEquals("abcde", adminResponse.getPwd());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void editAdmin_adminDoesNotExist(){
        Admin request = new Admin("Gabriel Zezin", "gabriel.otsuka@zup.com.br", "123");
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        adminService.editAdmin(2L, request);
    }

//    Delete Admin
    @Test
    public void deleteAdmin_success(){
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        adminService.deleteAdmin(1L);
        verify(adminRepository, times(1)).delete(admin);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteAdmin_adminDoesNotExist(){
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        adminService.deleteAdmin(2L);
    }

//    Change Admin Password
    @Test
    public void changeAdminPwd_success(){
        Admin request = new Admin("123");
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        Admin adminResponse = adminService.changeAdminPwd(1L, request);
        Assert.assertEquals("123", adminResponse.getPwd());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void changeAdminPwd_adminDoesNotExist(){
        Admin request = new Admin("123");
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        adminService.changeAdminPwd(2L, request);
    }

//                                                  Customer
//    Tests if Admin Service calls successfully Customer Service, not if the answer is right.
//    CustomerServiceTest tests if the answer is all right.
    @Test
    public void saveCustomer_success(){
        adminService.saveCustomer(customer);
        verify(customerService, times(1)).saveCustomer(customer);
    }

    @Test
    public void getCustomers_success(){
        adminService.getCustomers();
        verify(customerService, times(1)).getCustomers();
    }

    @Test
    public void getCustomerById_success(){
        adminService.getCustomerById(1L);
        verify(customerService, times(1)).getCustomerById(1L);
    }

    @Test
    public void editCustomer_success(){
        Customer newCustomer = new Customer("Gabriel Otsuka", "gabriel.otsuka@ufu.br", "abcd");
        adminService.editCustomer(1L, newCustomer);
        verify(customerService, times(1)).editCustomer(1L, newCustomer);
    }

    @Test
    public void getCustomerOrders_success(){
        adminService.getCustomerOrders(1L);
        verify(customerService, times(1)).getCustomerOrders(1L);
    }

    @Test
    public void getItemsByListOrder_success(){
        List<Order> orders = new ArrayList<>();
        adminService.getItemsByListOrder(orders);
        verify(customerService, times(1)).getItemsByListOrder(orders);
        verify(customerService,times(orders.size())).getItemsByOrder(any());
    }

    @Test
    public void changeCustomerPwd_success(){
        Customer request = new Customer("asdf");
        adminService.changeCustomerPwd(1L, request);
        verify(customerService, times(1)).changeCustomerPwd(1L, request);
    }

    @Test
    public void deleteCustomer_success(){
        adminService.deleteCustomer(1L);
        verify(customerService, times(1)).deleteCustomer(1L);
    }

//                                                  Product
//    Tests if Admin Service calls successfully Product Service, not if the answer is right.
//    ProductServiceTest tests if the answer is all right.
    @Test
    public void saveProduct_success(){
        adminService.saveProduct(product);
        verify(productService,times(1)).saveProduct(product);
    }

    @Test
    public void  getProducts_success(){
        adminService.getProducts();
        verify(productService,times(1)).getProducts();
    }

    @Test
    public void getProductById_success(){
        adminService.getProductById(1L);
        verify(productService,times(1)).getProductById(1L);
    }

    @Test
    public void editProduct_success(){
        Product newProd = new Product("pão", 0.5, 100);
        adminService.editProduct(newProd, 1L);
        verify(productService,times(1)).editProduct(newProd, 1L);
    }

    @Test
    public void deleteProduct_success(){
        adminService.deleteProduct(1l);
        verify(productService,times(1)).deleteProduct(1l);
    }

    @Test
    public void changeProductQuantity_success(){
        Product request = new Product(50);
        adminService.changeProductQuantity(1L, request);
        verify(productService,times(1)).changeProductQuantity(1L, request);
    }

    @Test
    public void changeProductPrice_success(){
        Product request = new Product(1.5);
        adminService.changeProductPrice(1L, request);
        verify(productService, times(1)).changeProductPrice(1L, request);
    }
}
