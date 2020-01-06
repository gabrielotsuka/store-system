package com.br.github.gabrielotsuka.storesystem.controllers;

import com.br.github.gabrielotsuka.storesystem.models.Employee;
import com.br.github.gabrielotsuka.storesystem.repositories.EmployeeRepository;
import com.br.github.gabrielotsuka.storesystem.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/admin/employees/register")
    public ModelAndView register(Employee employee){
        return employeeService.register("admin/employees/register", employee);
    }

    @PostMapping("admin/employees/save")
    public ModelAndView save(@Valid Employee employee, BindingResult result){
        employeeService.save(employee, result);
        return list();
    }

    @GetMapping("/admin/employees/list")
    public ModelAndView list() {
        return employeeService.list("admin/employees/list");
    }

    @GetMapping("admin/employees/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return register(employee.get());
    }

    @GetMapping("admin/employees/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        employeeService.remove(id);
        return list();
    }

}

