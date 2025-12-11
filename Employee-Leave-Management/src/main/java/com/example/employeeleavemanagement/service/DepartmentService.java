package com.example.employeeleavemanagement.service;

import com.example.employeeleavemanagement.Exception.DepartmentNotFoundException;
import com.example.employeeleavemanagement.dao.DepartmentRepository;
import com.example.employeeleavemanagement.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartmentService {

    Logger log = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentRepository departmentRepository;

//    ---------------------------------------Register Department-------------------------------
    public Department addDepartment(Department department)
    {
        log.info("Service: Log captured for {}", department.getId());
        return departmentRepository.save(department);
    }

//    ---------------------------------------List of Department-------------------------------
    public List<Department> getDepartment()
    {
        log.info("Service: Get all department");
        return departmentRepository.findAll();
    }
//    ---------------------------------------Department By Id-------------------------------
    public Department getDepartmentById(Long id)
    {
        log.info("Service: Get department by ID {}", id);
        return departmentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Department not found {}", id);
                    return new DepartmentNotFoundException("Department not found by id " + id);
                });
    }

//    ---------------------------------------Update Department details-------------------------------
    public Department updateDepartment(Long id, Department department)
    {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Department not found {}", id);
                    return new DepartmentNotFoundException("Department not found by id " + id);
                    });
        existingDepartment.setName(department.getName());
        log.info("Service: Update department details for ID {}", id);
        return departmentRepository.save(existingDepartment);
    }

//    ---------------------------------------Remove Department-------------------------------
    public Department removeDepartmentById(Long id)
    {
        Department dp = departmentRepository.findById(id)
                .orElseThrow(() -> {
                        log.error("Department not found {}", id);
                        return new DepartmentNotFoundException("Department not found by id " + id);
                });
        departmentRepository.delete(dp);
        log.info("Service: Delete department for ID {}", id);
        return dp;
    }




}
