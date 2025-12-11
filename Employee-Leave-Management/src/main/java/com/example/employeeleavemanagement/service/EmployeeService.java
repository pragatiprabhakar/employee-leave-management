package com.example.employeeleavemanagement.service;

import com.example.employeeleavemanagement.Exception.EmployeeNotFoundException;
import com.example.employeeleavemanagement.dao.EmployeeRepository;
import com.example.employeeleavemanagement.dao.UserRepository;
import com.example.employeeleavemanagement.model.Employee;
import com.example.employeeleavemanagement.model.Role;
import com.example.employeeleavemanagement.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployeeService {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepo;

//    ---------------------------------------Register Employee-------------------------------
    public Employee addEmployee(Employee employee)
    {
        Users user = new Users();
        user.setUsername(employee.getEmail());
        user.setPassword(passwordEncoder.encode("default123"));
        user.setRole(Role.EMPLOYEE);
        userRepo.save(user);
        log.info("Service: Creating new user");
        employee.setUser(user);
        log.info("Service: Creating new employee {}", employee.getEmail());
        log.debug("Service: Employee saved with id {}", employee.getId());
        return employeeRepository.save(employee);
    }

//    -------------------------------------------List of Employee-------------------------------
    public List<Employee> getAllEmployeeService()
    {
        log.info("Service: Fetching all employee");
        return employeeRepository.findAll();
    }

//    ---------------------------------------------Get Employee By Id----------------------------
    public Employee getEmployeeById(Long id)
    {
        log.info("Service: Fetching employee by ID");
        return employeeRepository.findById(id).orElseThrow(() -> {
            log.error("Employee not found {}", id);
            return new EmployeeNotFoundException("Employee not found by id " + id);
        });
    }

//    ---------------------------------------------Get Employee By Department Id-------------------
    public  List<Employee> getEmployeeByDepartmentId(Long id)
    {
        log.info("Service: Fetching all employee by Department");
        return employeeRepository.findByDepartmentId(id);
    }

//    ---------------------------------------------Update Employee Details--------------------------
    public Employee updateEmployee(Long id, Employee updatedEmployee)
    {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> {
            log.error("Employee not found {}", id);
            return new EmployeeNotFoundException("Employee not found by id " + id);
        });
        if(updatedEmployee.getName() != null)
        {
            existingEmployee.setName(updatedEmployee.getName());
        }
        if(updatedEmployee.getEmail() != null)
        {
            existingEmployee.setEmail(updatedEmployee.getEmail());
        }
        if(updatedEmployee.getDepartment() != null)
        {
            existingEmployee.setDepartment(updatedEmployee.getDepartment());
        }
        log.info("Service: Updating employee");
        return employeeRepository.save(existingEmployee);
    }

//    ---------------------------------------------Remove Employee Details--------------------------
    public Employee removeEmployeeById(Long id)
    {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
                log.error("Employee not found {}", id);
                return new EmployeeNotFoundException("Employee not found by id " + id);
    });
        employeeRepository.delete(employee);
        log.info("Service: Deleting employee");
        return employee;
    }


}
