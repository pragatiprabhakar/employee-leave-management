package com.example.employeeleavemanagement.Exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message)
    {
        super(message);
    }
}
