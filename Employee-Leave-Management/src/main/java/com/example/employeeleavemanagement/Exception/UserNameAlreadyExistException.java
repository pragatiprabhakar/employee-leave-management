package com.example.employeeleavemanagement.Exception;

public class UserNameAlreadyExistException extends RuntimeException {
    public UserNameAlreadyExistException(String message) {
      super(message);
    }
}
