package com.example.employeeleavemanagement.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class APIResponseLeave<T> {
    boolean success;
    String message;
}
