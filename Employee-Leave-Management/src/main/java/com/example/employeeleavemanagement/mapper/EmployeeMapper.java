package com.example.employeeleavemanagement.mapper;

import com.example.employeeleavemanagement.dto.EmployeeDTO;
import com.example.employeeleavemanagement.model.Employee;

public class EmployeeMapper {

    public static EmployeeDTO toDTO(Employee employee)
    {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());

        return dto;
    }
}
