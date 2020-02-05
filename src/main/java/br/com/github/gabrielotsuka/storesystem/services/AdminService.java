package br.com.github.gabrielotsuka.storesystem.services;

import br.com.github.gabrielotsuka.storesystem.controllers.request.customer.PasswordCustomerRequest;
import br.com.github.gabrielotsuka.storesystem.models.Order;
import br.com.github.gabrielotsuka.storesystem.controllers.request.admin.PasswordAdminRequest;
import br.com.github.gabrielotsuka.storesystem.controllers.request.customer.EditCustomerRequest;
import br.com.github.gabrielotsuka.storesystem.controllers.response.OrderResponse;
import br.com.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import br.com.github.gabrielotsuka.storesystem.models.Admin;
import br.com.github.gabrielotsuka.storesystem.models.Customer;
import br.com.github.gabrielotsuka.storesystem.models.Product;
import br.com.github.gabrielotsuka.storesystem.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final CustomerService customerService;
    private final AdminRepository adminRepository;
    private final ProductService productService;

    @Autowired
    public AdminService(CustomerService customerService,
                        AdminRepository adminRepository,
                        ProductService productService) {
        this.customerService = customerService;
        this.adminRepository = adminRepository;
        this.productService = productService;
    }

//  Admin
    private Admin verifyAdminExistence(Long id){
        Optional<Admin> user = adminRepository.findById(id);
        if(!user.isPresent())
            throw new ResourceNotFoundException("Admin not found. ID: "+id);
        else
            return user.get();
    }

    @Transactional
    public Admin saveAdmin(Admin admin){
        adminRepository.save(admin);
        return admin;
    }

    public List<Admin> getAdmins(){
        List<Admin> arr = adminRepository.findAll();
        return arr;
    }

    public Admin getAdminById(Long id){
        Admin admin = verifyAdminExistence(id);
        return admin;
    }

    @Transactional
    public Admin editAdmin(Long id, Admin newAdmin){
        Admin admin = verifyAdminExistence(id);
        admin.setName(newAdmin.getName());
        admin.setEmail(newAdmin.getEmail());
        adminRepository.save(admin);
        return admin;
    }

    public void deleteAdmin(Long id){
        Admin admin = verifyAdminExistence(id);
        adminRepository.delete(admin);
    }

    @Transactional
    public Admin changeAdminPwd(Long id, PasswordAdminRequest newPwd) {
        Admin admin = verifyAdminExistence(id);
        admin.setPwd(newPwd.getPwd());
        adminRepository.save(admin);
        return admin;
    }

//  Customer
    public Customer saveCustomer(Customer customer) {
        return customerService.saveCustomer(customer);
    }

    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    public Customer getCustomerById(Long id) {
        return customerService.getCustomerById(id);
    }

    public Customer editCustomer(Long id, EditCustomerRequest newCustomer) {
        return customerService.editCustomer(id, newCustomer);
    }

    public List<Order> getCustomerOrders(Long id) {
        return customerService.getCustomerOrders(id);
    }

    public List<OrderResponse> getItemsByListOrder(List<Order> orders) {
        return customerService.getItemsByListOrder(orders);
    }

    public Customer changeCustomerPwd(Long id, PasswordCustomerRequest newPwd) {
        return customerService.changeCustomerPwd(id, newPwd);
    }

    public void deleteCustomer(Long id) {
        customerService.deleteCustomer(id);
    }


//  Product
    public Product saveProduct(Product product){
        return productService.saveProduct(product);
    }

    public List<Product> getProducts(){
        return productService.getProducts();
    }

    public Product getProductById(Long id) {
        return productService.getProductById(id);
    }

    public Product editProduct(Product newProd, Long id) {
        return productService.editProduct(newProd, id);
    }

    public void deleteProduct(Long id) {
        productService.deleteProduct(id);
    }

    public Product changeProductQuantity(Long id, Product request) {
        return productService.changeProductQuantity(id, request);
    }

    public Product changeProductPrice(Long id, Product request) {
        return productService.changeProductPrice(id, request);
    }
}
