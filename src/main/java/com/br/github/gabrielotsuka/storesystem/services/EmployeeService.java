package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.controllers.request.EmployeeRequest;
import com.br.github.gabrielotsuka.storesystem.models.Employee;
import com.br.github.gabrielotsuka.storesystem.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public ModelAndView register(String endpoint, EmployeeRequest employee){
        ModelAndView mv = new ModelAndView((endpoint));
        mv.addObject("employee", employee);
        return mv;
    }

    public EmployeeRequest save(EmployeeRequest request, BindingResult result){
        Employee employee = new Employee(request.getName(), request.getInit_date(), request.getEmail());
        if(result.hasErrors())
           return request;
        employeeRepository.saveAndFlush(employee);
        return new EmployeeRequest();
    }

    public ModelAndView list(String endpoint){
        ModelAndView mv = new ModelAndView(endpoint);
        mv.addObject("listEmployees", employeeRepository.findAll());
        return mv;
    }

    public void remove(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        employeeRepository.delete(employee.get());
    }

    public EmployeeRequest edit(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        Employee em = employee.get();
        EmployeeRequest request = new EmployeeRequest();
        request.setName(em.getName());
        return request;
    }

}
