package com.example.employeeleavemanagement.service;

import com.example.employeeleavemanagement.Exception.LeaveRequestNotFoundException;
import com.example.employeeleavemanagement.dao.EmployeeRepository;
import com.example.employeeleavemanagement.dao.LeaveRequestRepository;
import com.example.employeeleavemanagement.dto.LeaveRequestAdminViewDTO;
import com.example.employeeleavemanagement.dto.LeaveRequestUserViewDTO;
import com.example.employeeleavemanagement.mapper.LeaveRequestAdminViewMapper;
import com.example.employeeleavemanagement.mapper.LeaveRequestUserViewMapper;
import com.example.employeeleavemanagement.model.Employee;
import com.example.employeeleavemanagement.model.LeaveRequest;
import com.example.employeeleavemanagement.model.LeaveStatus;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
public class LeaveRequestService {

    @Autowired
    LeaveRequestRepository leaveRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    private LeaveRequestUserViewMapper leaveRequestUserViewMapper = Mappers.getMapper(LeaveRequestUserViewMapper.class);

//---------------Leave apply(USER)---------------------------
    public void applyLeave(String username, LeaveRequest request)
    {
        Employee employee = employeeRepository.findByEmail(username);
        request.setEmployee(employee);

        if(request.getStartDate().isAfter(request.getEndDate()))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date cannot be after end date");
        }
        log.info("Leave Request is applied by ID {}", employee.getId());
        leaveRepository.save(request);
    }
//----------------My Leave (USER)----------------------------
    public List<LeaveRequestUserViewDTO> myLeave(String username)
    {
        Employee employee = employeeRepository.findByEmail(username);
        log.info("Fetch all Leave Request for employee ID{} ", employee.getId());
        return leaveRequestUserViewMapper.toDTOList(leaveRepository.findByEmployee(employee));
    }

//--------------Leave approve(ADMIN)----------------------------
    public LeaveRequest approveLeave (Long id)
    {
        LeaveRequest leaveRequest = leaveRepository.findById(id).orElseThrow(() -> {
            log.error("Leave Request not found");
            return new LeaveRequestNotFoundException("Leave Request not found");
        });
        if(leaveRequest.getStatus() != LeaveStatus.PENDING)
        {
            log.error("Leave Request approval failed");
            throw new LeaveRequestNotFoundException("No action can be taken in this state");
        }
        leaveRequest.setStatus(LeaveStatus.APPROVED);
        log.info("Leave Approved for leave request {} ", id);
        return leaveRepository.save(leaveRequest);
    }

//--------------Leave reject(ADMIN)-------------------------------
    public LeaveRequest rejectLeave(Long leaveId)
    {
        LeaveRequest leaveRequest = leaveRepository.findById(leaveId).orElseThrow(() -> {
            log.error("Leave Request not found");
            return new LeaveRequestNotFoundException("Leave Request not found");
        });
        if(leaveRequest.getStatus() != LeaveStatus.PENDING)
        {
            log.error("Leave Request rejection failed");
            throw new LeaveRequestNotFoundException("No action can be taken in this state");
        }
        leaveRequest.setStatus(LeaveStatus.REJECTED);
        log.info("Leave Rejected for leave request {} ", leaveId);
        return leaveRepository.save(leaveRequest);
    }

//--------------All leave request(ADMIN)---------------------------
    public List<LeaveRequestAdminViewDTO> allLeaveRequest()
    {
        log.info("Fetch all Leave Request");
        return LeaveRequestAdminViewMapper.toDTOList(leaveRepository.findAll());
    }

// --------------All leave request Pending(ADMIN)-------------------
    public List<LeaveRequestAdminViewDTO> allPendingLeaveRequest()
    {
        List<LeaveRequestAdminViewDTO> leaveRequest = LeaveRequestAdminViewMapper.toDTOList(leaveRepository.findAll());
        log.info("Fetch all pending Leave Request");
        return leaveRequest.stream().filter(E -> E.getStatus() == LeaveStatus.PENDING).toList();
    }
}
