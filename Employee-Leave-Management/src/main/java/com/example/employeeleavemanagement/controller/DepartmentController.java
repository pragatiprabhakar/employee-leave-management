package com.example.employeeleavemanagement.controller;

import com.example.employeeleavemanagement.model.Department;
import com.example.employeeleavemanagement.payload.APIResponse;
import com.example.employeeleavemanagement.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    //    -----------------------------Register Department-----------------------------------
    @PostMapping("/register")
    public ResponseEntity<APIResponse<Department>> addDepartment(@RequestBody Department department)
    {
        Department savedDept = departmentService.addDepartment(department);
        APIResponse<Department> response = new APIResponse<>(true, "department is added successfully ", savedDept);
        log.info("API HIT: POST /api/department/register — New Department is registered");
        return new ResponseEntity<>(response, HttpStatus.CREATED) ;
    }

    //    -------------------------------Get Department List----------------------------------
    @GetMapping("/allDepartment")
    public ResponseEntity<List<Department>> getDepartment()
    {
        log.info("API HIT: GET /api/department/allDepartment — List of all registered department");
        return ResponseEntity.ok(departmentService.getDepartment());
    }

    //    ------------------------------------Get Department By ID---------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id)
    {
        log.info("API HIT: GET /api/department/{} — Search Department with id ", id);
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    //    --------------------------------------Update Department Details----------------------------
    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse<Department>> updateDepartment(@PathVariable Long id, @RequestBody Department updatedDepartment)
    {
        Department d = departmentService.updateDepartment(id, updatedDepartment);
        APIResponse<Department> response = new APIResponse<>(true , "Detail is successfully updated", d);
        log.info("API HIT: PUT /api/department/update/{} — Modify Department with id ", id);
        return ResponseEntity.ok(response) ;
    }

    //    ----------------------------------------Delete Department Details---------------------------
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse<String>>removeDepartment(@PathVariable Long id)
    {

        Department dp = departmentService.removeDepartmentById(id);
        log.info("API HIT: DELETE /api/department/delete/{} — Delete Department with id ", id);
        return ResponseEntity.ok(new APIResponse<>(true, "Department is "+ dp.getName() +" removed " , null));
    }

}
