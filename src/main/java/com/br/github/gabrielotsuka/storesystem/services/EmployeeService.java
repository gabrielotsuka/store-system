package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.controllers.response.EmployeeResponse;
import com.br.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import com.br.github.gabrielotsuka.storesystem.models.Employee;
import com.br.github.gabrielotsuka.storesystem.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<EmployeeResponse> save(Employee employee){
        employeeRepository.save(employee);
        return new ResponseEntity<EmployeeResponse>(EmployeeResponse.toResponse(employee), HttpStatus.CREATED);
    }

    public List<EmployeeResponse> getUsers(){
        List<Employee> arr = employeeRepository.findAll();
        List<EmployeeResponse> response = new ArrayList<>();
        arr.forEach(temp -> {
            response.add(EmployeeResponse.toResponse(temp));
        });
        return response;
    }

    public ResponseEntity<EmployeeResponse> getUserById(Long id){
        Employee employee = verifyUserExistence(id);
        return new ResponseEntity<EmployeeResponse>(EmployeeResponse.toResponse(employee), HttpStatus.OK);
    }

    public ResponseEntity<EmployeeResponse> editUser(Long id, Employee newEmployee){
        Employee employee = verifyUserExistence(id);
        employee.setName(newEmployee.getName());
        employee.setEmail(newEmployee.getEmail());
        employee.setPwd(newEmployee.getPwd());
        employeeRepository.save(employee);
        return new ResponseEntity<EmployeeResponse>(EmployeeResponse.toResponse(employee), HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteUser(Long id){
        Employee employee = verifyUserExistence(id);
        employeeRepository.delete(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Employee verifyUserExistence(Long id){
        Optional<Employee> user = employeeRepository.findById(id);
        if(!user.isPresent())
            throw new ResourceNotFoundException("User not found. ID: "+id);
        else
            return user.get();
    }
}
