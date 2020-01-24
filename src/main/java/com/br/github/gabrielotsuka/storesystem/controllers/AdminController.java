package com.br.github.gabrielotsuka.storesystem.controllers;

import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.EditRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.PasswordRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.SaveRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.employee.SavingRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.product.ProductSavingRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.response.CustomerResponse;
import com.br.github.gabrielotsuka.storesystem.controllers.response.EmployeeResponse;
import com.br.github.gabrielotsuka.storesystem.models.Customer;
import com.br.github.gabrielotsuka.storesystem.models.Employee;
import com.br.github.gabrielotsuka.storesystem.models.Product;
import com.br.github.gabrielotsuka.storesystem.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

//   Customer
    @PostMapping(value = "/customer")
    public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody @Valid SaveRequest customer){
        Customer customerRet = adminService.saveCustomer(customer.toCustomer());
        return new ResponseEntity<>(CustomerResponse.toResponse(customerRet), HttpStatus.CREATED);
    }

    @GetMapping(value = "/customer")
    public ResponseEntity<List<CustomerResponse>> getCustomers(){
        return new ResponseEntity<>
                (CustomerResponse.toListResponse(adminService.getCustomers()),
                        HttpStatus.OK);
    }

    @GetMapping(value = "/customer/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable(value = "id") Long id){
        Customer response = adminService.getCustomerById(id);
        return new ResponseEntity<>(CustomerResponse.toResponse(response), HttpStatus.OK);
    }

    @PutMapping(value = "/customer/{id}")
    public ResponseEntity<CustomerResponse> editCustomer(@PathVariable(value = "id") Long id,
                                                         @RequestBody @Valid EditRequest newCustomer){
        Customer response = adminService.editCustomer(id, newCustomer);
        return new ResponseEntity<>(CustomerResponse.toResponse(response), HttpStatus.OK);
    }

    @PutMapping(value = "/customer/{id}/changePwd")
    public ResponseEntity<CustomerResponse> changeCustomerPwd(@PathVariable(value = "id") Long id,
                                                              @RequestBody @Valid PasswordRequest newPwd){
        Customer response = adminService.changeCustomerPwd(id, newPwd);
        return new ResponseEntity<>(CustomerResponse.toResponse(response), HttpStatus.OK);
    }

    @DeleteMapping(value = "/customer/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable(value = "id") Long id){
        adminService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//  Employee
    @PostMapping(value = "/employee")
    public ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody @Valid SavingRequest employee) {
        Employee response = adminService.saveEmployee(employee.toEmployee());
        return new ResponseEntity<>(EmployeeResponse.toResponse(response), HttpStatus.CREATED);
    }

    @GetMapping(value = "/employee")
    public ResponseEntity<List<EmployeeResponse>> getEmployees() {
        List<Employee> employees = adminService.getEmployees();
        return new ResponseEntity<>(EmployeeResponse.toListResponse(employees), HttpStatus.OK);
    }

    @GetMapping(value = "/employee/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById (@PathVariable(value = "id") Long id) {
        Employee employee = adminService.getEmployeeById(id);
        return new ResponseEntity<>(EmployeeResponse.toResponse(employee), HttpStatus.OK);
    }

    @PutMapping(value = "/employee/{id}")
    public ResponseEntity<EmployeeResponse> editEmployee(@PathVariable(value = "id") Long id,
                                                         @Valid @RequestBody com.br.github.gabrielotsuka.storesystem.controllers.request.employee.EditRequest newUser){
        Employee employee = adminService.editEmployee(id, newUser.toEmployee());
        return new ResponseEntity<>(EmployeeResponse.toResponse(employee), HttpStatus.OK);
    }

    @DeleteMapping(value = "/employee/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable(value = "id") Long id){
        adminService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/employee/{id}/changePwd")
    public ResponseEntity<EmployeeResponse> changeEmployeePwd(@PathVariable(value = "id") Long id,
                                                              @Valid @RequestBody PasswordRequest newPwd){
        Employee employee = adminService.changeEmployeePwd(id, newPwd);
        return new ResponseEntity<>(EmployeeResponse.toResponse(employee), HttpStatus.OK);
    }

//  Product
    @PostMapping(value = "/product")
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductSavingRequest product){
        return new ResponseEntity<>(adminService.saveProduct(product.toProduct()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/product")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(adminService.getProducts(), HttpStatus.OK);
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(adminService.getProductById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/product/{id}")
    public ResponseEntity<Product> editProduct(@RequestBody @Valid ProductSavingRequest newProd,
                                               @PathVariable(value = "id") Long id){
        return new ResponseEntity<>(adminService.editProduct(newProd.toProduct(), id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Long id){
        adminService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
