package com.br.github.gabrielotsuka.storesystem.controllers;

import com.br.github.gabrielotsuka.storesystem.models.Employee;
import com.br.github.gabrielotsuka.storesystem.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "admin/users/register", method = RequestMethod.GET)
    public String form() {
        return "admin/users/register";
    }

    @RequestMapping(value = "admin/users/register", method = RequestMethod.POST)
    public String form(Employee employee){
        employeeRepository.save(employee);
        return "redirect:/admin/users/register";
    }

    @RequestMapping("admin/users/list")
    public ModelAndView listEmployees() {
        ModelAndView mv=new ModelAndView("admin/users/list");
        Iterable<Employee> listEmployees = employeeRepository.findAll();
        mv.addObject("employee", listEmployees);
        return mv;
    }

}

