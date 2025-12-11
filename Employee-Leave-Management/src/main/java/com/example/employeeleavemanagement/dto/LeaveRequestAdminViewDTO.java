package com.example.employeeleavemanagement.dto;

import com.example.employeeleavemanagement.model.LeaveStatus;
import com.example.employeeleavemanagement.model.LeaveType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LeaveRequestAdminViewDTO {
    private Long id;
    private EmployeeDTO employeeDTO;
    private LeaveType leaveType ;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private LeaveStatus status = LeaveStatus.PENDING;
    private LocalDateTime appliedDate = LocalDateTime.now();
}
