package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.DtoEmployee;
import com.example.EmployeeManagementSystem.service.ServiceEmployeeIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class PageController {

    @Autowired
    private ServiceEmployeeIn serviceEmployee;

    // Show form page
    @GetMapping
    public String showForm() {
        return "index"; // loads index.jsp
    }

    // Save employee (from JSP form)
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute DtoEmployee employee) {
        serviceEmployee.createEmployee(employee);
        return "redirect:/employees/list";
    }

    // Show all employees (JSP output page)
    @GetMapping("/list")
    public String listEmployees(Model model) {
        model.addAttribute("employees", serviceEmployee.findAllEmployee());
        return "output"; // loads output.jsp
    }
}
