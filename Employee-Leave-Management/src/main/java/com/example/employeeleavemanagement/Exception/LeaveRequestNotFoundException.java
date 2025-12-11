package com.example.employeeleavemanagement.Exception;

public class LeaveRequestNotFoundException extends RuntimeException {
    public LeaveRequestNotFoundException(String message)
    {
      super(message);
    }
}
