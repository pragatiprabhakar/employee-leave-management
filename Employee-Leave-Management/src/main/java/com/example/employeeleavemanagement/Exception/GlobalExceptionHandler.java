package com.example.employeeleavemanagement.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> HandleEmployeeNotFoundException(EmployeeNotFoundException e)
    {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Employee Not Found");
        error.put("message", e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> HandleDepartmentNotFoundException(DepartmentNotFoundException e)
    {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Department Not Found");
        error.put("message", e.getMessage());
        return  new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> HandleUserNameAlreadyExistException(UserNameAlreadyExistException e)
    {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("error", "User Already Exist");
        error.put("message", e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> HandleLeaveRequestNotFoundException(LeaveRequestNotFoundException e)
    {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Leave Request Not Exist");
        error.put("message", e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> HandleInvalidLeaveStatusException(InvalidLeaveStatusException e)
    {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.EXPECTATION_FAILED.value());
        error.put("error", "No action can be taken in this state");
        error.put("message", e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.EXPECTATION_FAILED);
    }

}
