package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.EditRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.PasswordRequest;
import com.br.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import com.br.github.gabrielotsuka.storesystem.models.Customer;
import com.br.github.gabrielotsuka.storesystem.models.Employee;
import com.br.github.gabrielotsuka.storesystem.models.Product;
import com.br.github.gabrielotsuka.storesystem.repositories.CustomerRepository;
import com.br.github.gabrielotsuka.storesystem.repositories.EmployeeRepository;
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
    private final EmployeeRepository employeeRepository;
    @Autowired
    private final ProductRepository productRepository;

    public AdminService(CustomerRepository customerRepository, EmployeeRepository employeeRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
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

    public Customer editCustomer(Long id, EditRequest newCustomer) {
        Customer oldCustomer = verifyCustomerExistence(id);
        oldCustomer.setEmail(newCustomer.getEmail());
        oldCustomer.setName(newCustomer.getName());
        customerRepository.save(oldCustomer);
        return oldCustomer;
    }

    public Customer changeCustomerPwd(Long id, PasswordRequest newPwd) {
        Customer customer = verifyCustomerExistence(id);
        customer.setPwd(newPwd.getPwd());
        customerRepository.save(customer);
        return customer;
    }

    public void deleteCustomer(Long id) {
        Customer customer = verifyCustomerExistence(id);
        customerRepository.delete(customer);
    }
//  Employee
    private Employee verifyEmployeeExistence(Long id){
        Optional<Employee> user = employeeRepository.findById(id);
        if(!user.isPresent())
            throw new ResourceNotFoundException("Employee not found. ID: "+id);
        else
            return user.get();
    }

    public Employee saveEmployee(Employee employee){
        employeeRepository.save(employee);
        return employee;
    }

    public List<Employee> getEmployees(){
        List<Employee> arr = employeeRepository.findAll();
        return arr;
    }

    public Employee getEmployeeById(Long id){
        Employee employee = verifyEmployeeExistence(id);
        return employee;
    }

    public Employee editEmployee(Long id, Employee newEmployee){
        Employee employee = verifyEmployeeExistence(id);
        employee.setName(newEmployee.getName());
        employee.setEmail(newEmployee.getEmail());
        employeeRepository.save(employee);
        return employee;
    }

    public void deleteEmployee(Long id){
        Employee employee = verifyEmployeeExistence(id);
        employeeRepository.delete(employee);
    }

    public Employee changeEmployeePwd(Long id, PasswordRequest newPwd) {
        Employee employee = verifyEmployeeExistence(id);
        employee.setPwd(newPwd.getPwd());
        employeeRepository.save(employee);
        return employee;
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
