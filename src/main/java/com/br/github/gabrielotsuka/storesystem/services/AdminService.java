package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.EditCustomerRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.PasswordCustomerRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.admin.PasswordAdminRequest;
import com.br.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import com.br.github.gabrielotsuka.storesystem.models.Admin;
import com.br.github.gabrielotsuka.storesystem.models.Customer;
import com.br.github.gabrielotsuka.storesystem.models.Product;
import com.br.github.gabrielotsuka.storesystem.repositories.CustomerRepository;
import com.br.github.gabrielotsuka.storesystem.repositories.AdminRepository;
import com.br.github.gabrielotsuka.storesystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final AdminRepository adminRepository;
    @Autowired
    private final ProductRepository productRepository;

    public AdminService(CustomerRepository customerRepository, AdminRepository adminRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.adminRepository = adminRepository;
        this.productRepository = productRepository;
    }

//  Customer
    private Customer verifyCustomerExistence(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(!customer.isPresent())
            throw new ResourceNotFoundException("Customer not found. ID: " + id);
        else
            return customer.get();
    }

    public Customer saveCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        Customer customer = verifyCustomerExistence(id);
        return customer;
    }

    public Customer editCustomer(Long id, EditCustomerRequest newCustomer) {
        Customer oldCustomer = verifyCustomerExistence(id);
        oldCustomer.setEmail(newCustomer.getEmail());
        oldCustomer.setName(newCustomer.getName());
        customerRepository.save(oldCustomer);
        return oldCustomer;
    }

    public Customer changeCustomerPwd(Long id, PasswordCustomerRequest newPwd) {
        Customer customer = verifyCustomerExistence(id);
        customer.setPwd(newPwd.getPwd());
        customerRepository.save(customer);
        return customer;
    }

    public void deleteCustomer(Long id) {
        Customer customer = verifyCustomerExistence(id);
        customerRepository.delete(customer);
    }
//  Admin
    private Admin verifyAdminExistence(Long id){
        Optional<Admin> user = adminRepository.findById(id);
        if(!user.isPresent())
            throw new ResourceNotFoundException("Admin not found. ID: "+id);
        else
            return user.get();
    }

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

    public Admin changeAdminPwd(Long id, PasswordAdminRequest newPwd) {
        Admin admin = verifyAdminExistence(id);
        admin.setPwd(newPwd.getPwd());
        adminRepository.save(admin);
        return admin;
    }

//  Product
    private Product verifyProductExistence(Long id) {
        Optional<Product> prod = productRepository.findById(id);
        if (!prod.isPresent())
            throw new ResourceNotFoundException("Product not found. ID: " + id);
        else
            return prod.get();
    }

    public Product saveProduct(Product product){
        productRepository.save(product);
        return product;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return verifyProductExistence(id);
    }

    public Product editProduct(Product newProd, Long id) {
        Product oldProd = verifyProductExistence(id);
        oldProd.setName(newProd.getName());
        oldProd.setPrice(newProd.getPrice());
        oldProd.setQuantity(newProd.getQuantity());
        productRepository.save(oldProd);
        return oldProd;
    }

    public void deleteProduct(Long id) {
        Product prod = verifyProductExistence(id);
        productRepository.delete(prod);
    }

}
