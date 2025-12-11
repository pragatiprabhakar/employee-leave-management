package com.example.employeeleavemanagement.dto;

import com.example.employeeleavemanagement.model.Department;
import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private Department department;
}
