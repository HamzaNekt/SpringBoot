package com.employees.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employees.webapp.model.Employee;
import com.employees.webapp.service.EmployeeService;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    /**
     * Affiche la liste de tous les employés
     */
    @GetMapping("/")
    public String employees(Model model) {
        System.out.println("EmployeeController *** employees(Model) ");
        Iterable<Employee> listEmployee = service.getEmployees();
        model.addAttribute("employees", listEmployee);
        return "home";
    }

    @PostMapping("/saveEmployee")
    public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
        service.saveEmployee(employee);
        return new ModelAndView("redirect:/");
    }

    /**
     * Affiche le formulaire de modification d'un employé existant
     */
    @PutMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") final int id, Model model) {
        System.out.println("EmployeeController *** updateEmployee() id=" + id);
        Employee employee = service.getEmployee(id);
        model.addAttribute("employee", employee);
        return "formUpdateEmployee";
    }

    /**
     * Delete - Delete an employee
     * @param id - The id of the employee to delete
     */
    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable("id") final Long id) {
        service.deleteEmployee(id);
    }
}