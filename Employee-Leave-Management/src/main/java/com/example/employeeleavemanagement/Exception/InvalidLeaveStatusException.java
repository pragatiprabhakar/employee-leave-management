package com.example.employeeleavemanagement.Exception;

public class InvalidLeaveStatusException extends RuntimeException {
    public InvalidLeaveStatusException(String message) {
        super(message);
    }
}
