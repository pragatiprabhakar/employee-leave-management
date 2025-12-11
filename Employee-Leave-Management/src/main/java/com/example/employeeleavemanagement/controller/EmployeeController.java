package com.example.employeeleavemanagement.controller;

import com.example.employeeleavemanagement.model.Employee;
import com.example.employeeleavemanagement.payload.APIResponse;
import com.example.employeeleavemanagement.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

//    -----------------------------Register Employee-----------------------------------
    @PostMapping("/register")
    public ResponseEntity<APIResponse<Employee>> addEmployee(@RequestBody Employee employee)
    {
        Employee e = employeeService.addEmployee(employee);
        APIResponse<Employee> response = new APIResponse<>(true, "employee added successfully", e);
        log.info("API HIT: POST /api/employee/register — Employee registered successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    -------------------------------Get Employee List----------------------------------
    @GetMapping("/allEmployee")
    public ResponseEntity<List<Employee>> getAllEmployees()
    {
        List<Employee> employees = employeeService.getAllEmployeeService();
        log.info("API HIT: GET /api/employee/allEmployee — List of all registered employee");
        return ResponseEntity.ok(employees) ;
    }

//    ---------------------------------Get Employee By Department--------------------------
    @GetMapping("/dept/{id}")
    public ResponseEntity<List<Employee>> getByDepartmentId(@PathVariable Long id)
    {
        List<Employee> e = employeeService.getEmployeeByDepartmentId(id);
        log.info("API HIT: GET /api/employee/dept/{} — List of all registered employee in particular department", id);
        return ResponseEntity.ok(e);
    }

//    ------------------------------------Get Employee By ID---------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id)
    {
        Employee e = employeeService.getEmployeeById(id);
        log.info("API HIT: GET /api/employee/{} — Search Employee with id ", id);
        return ResponseEntity.ok(e);
    }

//    --------------------------------------Update Employee Details----------------------------
    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse<Employee>> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee)
    {
        APIResponse<Employee> response = new APIResponse<>(true, "Details are updated", employeeService.updateEmployee(id, updatedEmployee));
        log.info("API HIT: PUT /api/employee/update/{} — Modify Employee with id ", id);
        return ResponseEntity.ok(response);
    }

//    ----------------------------------------Delete Employee Details---------------------------
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse<Employee>> removeEmployeeById(@PathVariable Long id)
    {
        APIResponse<Employee> employee = new APIResponse<>(true, "Employee is removed", employeeService.removeEmployeeById(id));
        log.info("API HIT: DELETE /api/employee/delete/{} — Delete Employee with id ", id);
        return ResponseEntity.ok(employee);
    }
}


//-----------------------------------------------------------------------------------------------------------------
//    @GetMapping("/session")
//    public String sessionInfo(HttpServletRequest request)
//    {
//        return "Hello" + request.getSession().getId();
//    }

//    @GetMapping("/csrf")
//    public CsrfToken getCsrfToken(HttpServletRequest request)
//    {
//        System.out.println("Session ID: " + request.getSession().getId());
//        return (CsrfToken) request.getAttribute("_csrf");
//    }