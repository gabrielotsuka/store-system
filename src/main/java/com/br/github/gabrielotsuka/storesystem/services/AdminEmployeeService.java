package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.controllers.request.customer.PasswordRequest;
import com.br.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import com.br.github.gabrielotsuka.storesystem.models.Employee;
import com.br.github.gabrielotsuka.storesystem.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminEmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public AdminEmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee){
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

    private Employee verifyEmployeeExistence(Long id){
        Optional<Employee> user = employeeRepository.findById(id);
        if(!user.isPresent())
            throw new ResourceNotFoundException("Employee not found. ID: "+id);
        else
            return user.get();
    }
}
