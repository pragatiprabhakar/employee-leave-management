package com.example.employeeleavemanagement.mapper;

import com.example.employeeleavemanagement.dto.LeaveRequestUserViewDTO;
import com.example.employeeleavemanagement.model.LeaveRequest;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
@Mapper(componentModel = "spring")
public interface LeaveRequestUserViewMapper {


        LeaveRequestUserViewDTO toDTO(LeaveRequest request);
        default List<LeaveRequestUserViewDTO> toDTOList(List<LeaveRequest> entities)
        {
            List<LeaveRequestUserViewDTO> list = new ArrayList<>();
            for (LeaveRequest entity: entities)
            {
                list.add(toDTO(entity));
            }
            return  list;
        }

}
//    public static LeaveRequestUserViewDTO toDTO(LeaveRequest request)
//    {
//        LeaveRequestUserViewDTO dto = new LeaveRequestUserViewDTO();
//        dto.setId(request.getId());
//        dto.setLeaveType(request.getLeaveType());
//        dto.setStartDate(request.getStartDate());
//        dto.setEndDate(request.getEndDate());
//        dto.setReason(request.getReason());
//        dto.setStatus(request.getStatus());
//        dto.setAppliedDate(request.getAppliedDate());
//        return dto;
//    }
//
//    public static List<LeaveRequestUserViewDTO> toDTOList(List<LeaveRequest> entities)
//    {
//        List<LeaveRequestUserViewDTO> list = new ArrayList<>();
//        for (LeaveRequest entity: entities)
//        {
//            list.add(toDTO(entity));
//        }
//        return  list;
//    }