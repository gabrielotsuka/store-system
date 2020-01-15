package com.br.github.gabrielotsuka.storesystem.controllers;

import com.br.github.gabrielotsuka.storesystem.controllers.request.EmployeeRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.response.EmployeeResponse;
import com.br.github.gabrielotsuka.storesystem.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/employee")
    public ResponseEntity<EmployeeResponse> save(@RequestBody @Valid EmployeeRequest dto) {
        return employeeService.save(dto.toUser());
    }

    @GetMapping(value = "/employee")
    public List<EmployeeResponse> getUsers() {
        return employeeService.getUsers();
    }

    @GetMapping(value = "employee/{id}")
    public ResponseEntity<EmployeeResponse> getUserById(@PathVariable(value = "id") Long id) {
        return employeeService.getUserById(id);
    }

    @PutMapping(value = "employee/{id}")
    public ResponseEntity<EmployeeResponse> editUser(@PathVariable(value = "id") Long id,
                                                     @Valid @RequestBody EmployeeRequest newUser){
        return employeeService.editUser(id, newUser.toUser());
    }

    @DeleteMapping(value = "employee/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long id){
        return employeeService.deleteUser(id);
    }
}
