package com.br.github.gabrielotsuka.storesystem.controllers;

import com.br.github.gabrielotsuka.storesystem.controllers.request.admin.EditAdminRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.admin.PasswordAdminRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.admin.SaveAdminRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.EditCustomerRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.PasswordCustomerRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.SaveCustomerRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.product.AddToProductRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.product.ProductSavingRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.response.AdminResponse;
import com.br.github.gabrielotsuka.storesystem.controllers.response.CustomerResponse;
import com.br.github.gabrielotsuka.storesystem.controllers.response.OrderResponse;
import com.br.github.gabrielotsuka.storesystem.models.Admin;
import com.br.github.gabrielotsuka.storesystem.models.Customer;
import com.br.github.gabrielotsuka.storesystem.models.Order;
import com.br.github.gabrielotsuka.storesystem.models.Product;
import com.br.github.gabrielotsuka.storesystem.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
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
    public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody @Valid SaveCustomerRequest customer){
        Customer customerRet = adminService.saveCustomer(customer.toCustomer());
        return new ResponseEntity<>(CustomerResponse.toResponse(customerRet), HttpStatus.CREATED);
    }

    @GetMapping(value = "/customer")
    public ResponseEntity<List<CustomerResponse>> getCustomers(){
        return new ResponseEntity<>(CustomerResponse.toListResponse(adminService.getCustomers()),
                        HttpStatus.OK);
    }

    @GetMapping(value = "/customer/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable(value = "id") Long id){
        Customer response = adminService.getCustomerById(id);
        return new ResponseEntity<>(CustomerResponse.toResponse(response), HttpStatus.OK);
    }

    @GetMapping(value = "/customer/{id}/getOrders")
    public ResponseEntity<List<OrderResponse>> getCustomerOrders (@PathVariable(value = "id") Long id){
        List<Order> orders = adminService.getCustomerOrders(id);
        return new ResponseEntity<>(adminService.getItemsByListOrder(orders), HttpStatus.OK);
    }

    @PutMapping(value = "/customer/{id}")
    public ResponseEntity<CustomerResponse> editCustomer(@PathVariable(value = "id") Long id,
                                                         @RequestBody @Valid EditCustomerRequest newCustomer){
        Customer response = adminService.editCustomer(id, newCustomer);
        return new ResponseEntity<>(CustomerResponse.toResponse(response), HttpStatus.OK);
    }

    @PutMapping(value = "/customer/{id}/changePwd")
    public ResponseEntity<CustomerResponse> changeCustomerPwd(@PathVariable(value = "id") Long id,
                                                              @RequestBody @Valid PasswordCustomerRequest newPwd){
        Customer response = adminService.changeCustomerPwd(id, newPwd);
        return new ResponseEntity<>(CustomerResponse.toResponse(response), HttpStatus.OK);
    }

    @DeleteMapping(value = "/customer/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable(value = "id") Long id){
        adminService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//  Admin
    @PostMapping(value = "/admin")
    public ResponseEntity<AdminResponse> saveAdmin(@RequestBody @Valid SaveAdminRequest admin) {
        Admin response = adminService.saveAdmin(admin.toAdmin());
        return new ResponseEntity<>(AdminResponse.toResponse(response), HttpStatus.CREATED);
    }

    @GetMapping(value = "/admin")
    public ResponseEntity<List<AdminResponse>> getAdmins() {
        List<Admin> admins = adminService.getAdmins();
        return new ResponseEntity<>(AdminResponse.toListResponse(admins), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/{id}")
    public ResponseEntity<AdminResponse> getAdminById (@PathVariable(value = "id") Long id) {
        Admin admin = adminService.getAdminById(id);
        return new ResponseEntity<>(AdminResponse.toResponse(admin), HttpStatus.OK);
    }

    @PutMapping(value = "/admin/{id}")
    public ResponseEntity<AdminResponse> editAdmin(@PathVariable(value = "id") Long id,
                                                      @Valid @RequestBody EditAdminRequest newUser){
        Admin admin = adminService.editAdmin(id, newUser.toAdmin());
        return new ResponseEntity<>(AdminResponse.toResponse(admin), HttpStatus.OK);
    }

    @DeleteMapping(value = "/admin/{id}")
    public ResponseEntity<Object> deleteAdmin(@PathVariable(value = "id") Long id){
        adminService.deleteAdmin(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/admin/{id}/changePwd")
    public ResponseEntity<AdminResponse> changeAdminPwd(@PathVariable(value = "id") Long id,
                                                           @Valid @RequestBody PasswordAdminRequest newPwd){
        Admin admin = adminService.changeAdminPwd(id, newPwd);
        return new ResponseEntity<>(AdminResponse.toResponse(admin), HttpStatus.OK);
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
                                               @PathVariable Long id){
        return new ResponseEntity<>(adminService.editProduct(newProd.toProduct(), id), HttpStatus.OK);
    }

//    SUM OR SUBTRACT TO TOTAL QUANTITY OF STOCK
    @PutMapping(value = "/product/{id}/changeProdQtt")
    public ResponseEntity<Product> changeProductQuantity(@RequestBody @Valid AddToProductRequest request,
                                                @PathVariable Long id){
        return new ResponseEntity<>(adminService.changeProductQuantity(id, request.toProduct()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Long id){
        adminService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
