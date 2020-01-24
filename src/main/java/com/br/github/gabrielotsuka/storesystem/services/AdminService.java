package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.EditCustomerRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.PasswordCustomerRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.admin.PasswordAdminRequest;
import com.br.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import com.br.github.gabrielotsuka.storesystem.models.Admin;
import com.br.github.gabrielotsuka.storesystem.models.Customer;
import com.br.github.gabrielotsuka.storesystem.models.Product;
import com.br.github.gabrielotsuka.storesystem.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private final CustomerService customerService;
    @Autowired
    private final AdminRepository adminRepository;
    @Autowired
    private final ProductService productService;

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

}
