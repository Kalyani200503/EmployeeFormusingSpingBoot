package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.DtoEmployee;
import com.example.EmployeeManagementSystem.exception.ExceptionEmployee;
import com.example.EmployeeManagementSystem.service.ServiceEmployeeIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/employee")
public class ControllerEmployee {

    @Autowired
    private ServiceEmployeeIn serviceEmployee;

    // Create employee
    @PostMapping
    public ResponseEntity<DtoEmployee> createEmployee(@RequestBody DtoEmployee dtaEmployee){
        DtoEmployee e = serviceEmployee.createEmployee(dtaEmployee);
        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }

    // Get all employees
 // Get all employees
    @GetMapping
    public ResponseEntity<List<DtoEmployee>> findAllEmployee() {
        List<DtoEmployee> employees = serviceEmployee.findAllEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    // Get employee by numeric ID
    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<DtoEmployee> findById(@PathVariable("id") int id){
        DtoEmployee e = serviceEmployee.findById(id);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    // Update employee by numeric ID
    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<DtoEmployee> updateEmployee(@PathVariable("id") int id,
                                                      @RequestBody DtoEmployee updatedemployee){
        DtoEmployee updatedemp = serviceEmployee.updateEmployee(id, updatedemployee);
        return new ResponseEntity<>(updatedemp, HttpStatus.OK);
    }

    // Delete employee by numeric ID
    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id){
        try {
            serviceEmployee.deleteEmployee(id);
            return ResponseEntity.ok("✅ Employee deleted successfully");
        } catch (ExceptionEmployee ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("⚠ Error: " + ex.getMessage());
        }
    }

}
