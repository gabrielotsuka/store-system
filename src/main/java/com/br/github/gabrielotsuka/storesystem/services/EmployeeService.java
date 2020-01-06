package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.controllers.EmployeeController;
import com.br.github.gabrielotsuka.storesystem.models.Employee;
import com.br.github.gabrielotsuka.storesystem.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeController employeeController;

    public ModelAndView register(String endpoint, Employee employee){
        ModelAndView mv = new ModelAndView((endpoint));
        mv.addObject("employee", employee);
        return mv;
    }

    public void save(@Valid Employee employee, BindingResult result){
        if(result.hasErrors())
            employeeController.register(employee);
        employeeRepository.saveAndFlush(employee);
        employeeController.register(new Employee());
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

}
