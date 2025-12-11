package com.example.employeeleavemanagement.mapper;

import com.example.employeeleavemanagement.dto.LeaveRequestAdminViewDTO;
import com.example.employeeleavemanagement.model.LeaveRequest;

import java.util.ArrayList;
import java.util.List;

public class LeaveRequestAdminViewMapper {


    public static LeaveRequestAdminViewDTO toDTO(LeaveRequest request)
    {
        LeaveRequestAdminViewDTO dto = new LeaveRequestAdminViewDTO();
        dto.setId(request.getId());
        dto.setEmployeeDTO(EmployeeMapper.toDTO(request.getEmployee()));
        dto.setLeaveType(request.getLeaveType());
        dto.setStartDate(request.getStartDate());
        dto.setEndDate(request.getEndDate());
        dto.setReason(request.getReason());
        dto.setStatus(request.getStatus());
        dto.setAppliedDate(request.getAppliedDate());
        return dto;
    }

    public static List<LeaveRequestAdminViewDTO> toDTOList(List<LeaveRequest> entities)
    {
        List<LeaveRequestAdminViewDTO> list = new ArrayList<>();
        for (LeaveRequest entity: entities)
        {
            list.add(toDTO(entity));
        }
        return  list;
    }


}
