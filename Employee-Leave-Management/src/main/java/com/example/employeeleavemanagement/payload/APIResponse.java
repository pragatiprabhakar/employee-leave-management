package com.example.employeeleavemanagement.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class APIResponse<T>{
    boolean success;
    String message;
    T data;
}
