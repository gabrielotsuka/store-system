package com.br.github.gabrielotsuka.storesystem.controllers;

import com.br.github.gabrielotsuka.storesystem.models.Employee;
import com.br.github.gabrielotsuka.storesystem.repositories.EmployeeRepository;
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

    @GetMapping("/admin/employees/register")
    public ModelAndView register(Employee employee){
        ModelAndView mv = new ModelAndView(("admin/employees/register"));
        mv.addObject("employee", employee);
        return mv;
    }

    @GetMapping("/admin/employees/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("admin/employees/list");
        mv.addObject("listEmployees", employeeRepository.findAll());
        return mv;
    }

    @PostMapping("admin/employees/save")
    public ModelAndView save(@Valid Employee employee, BindingResult result){
        if(result.hasErrors()){
            return register(employee);
        }
        employeeRepository.saveAndFlush(employee);
        return register(new Employee());
    }

    @GetMapping("admin/employees/edit/{email}")
    public ModelAndView edit(@PathVariable("email") String email) {
        Optional<Employee> employee = employeeRepository.findById(email);
        return register(employee.get());
    }

    @GetMapping("admin/employees/remove/{email}")
    public ModelAndView remove(@PathVariable("email") String email){
        Optional<Employee> employee = employeeRepository.findById(email);
        employeeRepository.delete(employee.get());
        return list();
    }

}

