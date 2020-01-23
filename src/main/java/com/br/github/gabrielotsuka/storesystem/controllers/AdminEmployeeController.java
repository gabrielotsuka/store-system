package com.br.github.gabrielotsuka.storesystem.controllers;

import com.br.github.gabrielotsuka.storesystem.controllers.request.employee.EditRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.employee.SavingRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.PasswordRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.response.EmployeeResponse;
import com.br.github.gabrielotsuka.storesystem.models.Employee;
import com.br.github.gabrielotsuka.storesystem.services.AdminEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/employee")
public class AdminEmployeeController {

    private final AdminEmployeeService adminEmployeeService;
    @Autowired
    public AdminEmployeeController(AdminEmployeeService adminEmployeeService) {
        this.adminEmployeeService = adminEmployeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> save(@RequestBody @Valid SavingRequest employee) {
        Employee response = adminEmployeeService.save(employee.toEmployee());
        return new ResponseEntity<EmployeeResponse>(EmployeeResponse.toResponse(response), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getEmployees() {
        List<Employee> employees = adminEmployeeService.getEmployees();
        return new ResponseEntity<List<EmployeeResponse>>(EmployeeResponse.toListResponse(employees), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById (@PathVariable(value = "id") Long id) {
        Employee employee = adminEmployeeService.getEmployeeById(id);
        return new ResponseEntity<EmployeeResponse>(EmployeeResponse.toResponse(employee), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EmployeeResponse> editEmployee(@PathVariable(value = "id") Long id,
                                                         @Valid @RequestBody EditRequest newUser){
        Employee employee = adminEmployeeService.editEmployee(id, newUser.toEmployee());
        return new ResponseEntity<EmployeeResponse>(EmployeeResponse.toResponse(employee), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable(value = "id") Long id){
        adminEmployeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/{id}/changePwd")
    public ResponseEntity<EmployeeResponse> changeEmployeePwd(@PathVariable(value = "id") Long id,
                                                              @Valid @RequestBody PasswordRequest newPwd){
        Employee employee = adminEmployeeService.changeEmployeePwd(id, newPwd);
        return new ResponseEntity<EmployeeResponse>(EmployeeResponse.toResponse(employee), HttpStatus.OK);
    }
}
