package com.avinash.kumar.module2.controllers;

import com.avinash.kumar.module2.dto.EmployeeDTO;
import com.avinash.kumar.module2.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{empId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name="empId") Long id){
        Optional<EmployeeDTO> employeeDTO =  employeeService.findById(id);
        return employeeDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping()
    public List<EmployeeDTO> getAll(@RequestParam(required = false) String age){
        return employeeService.findAll();
    }

    @PostMapping()
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        EmployeeDTO createdEmployee =  employeeService.save(employeeDTO);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path="/{empId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO,@PathVariable Long empId){
        EmployeeDTO updatedEmployee =  employeeService.updateEmployeeById(employeeDTO,empId);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping(path="/{empId}")
    public void deleteById(@PathVariable Long empId){
        employeeService.deleteEmployeeById(empId);
    }
    @PatchMapping(path="/{empId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployee(@PathVariable Long empId, @RequestBody Map<String,Object> updates){
        EmployeeDTO updatedEmployee = employeeService.updatePartialEmployee(empId,updates);
        return ResponseEntity.ok(updatedEmployee);
    }
}
