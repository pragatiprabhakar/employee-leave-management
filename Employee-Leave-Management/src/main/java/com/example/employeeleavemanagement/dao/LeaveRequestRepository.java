package com.example.employeeleavemanagement.dao;

import com.example.employeeleavemanagement.model.Employee;
import com.example.employeeleavemanagement.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployee(Employee employee);
}
