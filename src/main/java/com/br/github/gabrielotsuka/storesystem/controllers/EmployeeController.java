package com.br.github.gabrielotsuka.storesystem.controllers;

import com.br.github.gabrielotsuka.storesystem.controllers.request.EmployeeRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.PasswordRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.response.EmployeeResponse;
import com.br.github.gabrielotsuka.storesystem.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> save(@RequestBody @Valid EmployeeRequest employee) {
        return employeeService.save(employee.toEmployee());
    }

    @GetMapping
    public List<EmployeeResponse> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById
            (@PathVariable(value = "id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EmployeeResponse> editEmployee(@PathVariable(value = "id") Long id,
                                                         @Valid @RequestBody EmployeeRequest newUser){
        return employeeService.editEmployee(id, newUser.toEmployee());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable(value = "id") Long id){
        return employeeService.deleteEmployee(id);
    }

    @PutMapping(value = "/{id}/changePwd")
    public ResponseEntity<EmployeeResponse> changeEmployeePwd(@PathVariable(value = "id") Long id,
                                                              @Valid @RequestBody PasswordRequest newPwd){
        return employeeService.changeEmployeePwd(id, newPwd);
    }
}
