package com.br.github.gabrielotsuka.storesystem.controllers;

import com.br.github.gabrielotsuka.storesystem.models.Employee;
import com.br.github.gabrielotsuka.storesystem.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "admin/employees/register", method = RequestMethod.GET)
    public String form() {
        return "admin/employees/register";
    }

    @RequestMapping(value = "admin/employees/register", method = RequestMethod.POST)
    public String form(Employee employee){
        employeeRepository.save(employee);
        return "redirect:/admin/employees/register";
    }

    @GetMapping("/admin/employees/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("admin/employees/list");
        mv.addObject("listEmployees", employeeRepository.findAll());
        return mv;
    }

}

