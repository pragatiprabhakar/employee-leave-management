package com.example.employeeleavemanagement.controller;

import com.example.employeeleavemanagement.dto.LeaveRequestAdminViewDTO;
import com.example.employeeleavemanagement.dto.LeaveRequestUserViewDTO;
import com.example.employeeleavemanagement.model.LeaveRequest;
import com.example.employeeleavemanagement.payload.APIResponseLeave;
import com.example.employeeleavemanagement.service.LeaveRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("/api/leave")
public class LeaveRequestController {

    @Autowired
    LeaveRequestService leaveRequestService;

    //    -------------------------------Leave Apply----------------------------------
    @PreAuthorize(("hasRole('USER')"))
    @PostMapping("/apply")
    public ResponseEntity<APIResponseLeave<String>> applyLeave(@RequestBody LeaveRequest request)
     {
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         String username = auth.getName();
         leaveRequestService.applyLeave(username, request);
         APIResponseLeave<String> response = new APIResponseLeave<>(true, "Leave is applied successfully");
         log.info("API HIT: POST /api/leave/apply — Employee applying for leave");
         return new ResponseEntity<>(response, HttpStatus.CREATED);
     }


    //    -------------------------------Employee Leave Request----------------------------------
    @PreAuthorize(("hasRole('USER')"))
    @GetMapping("/myLeave")
     public ResponseEntity<List<LeaveRequestUserViewDTO>> myLeave()
     {
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         String username = auth.getName();
         List<LeaveRequestUserViewDTO> leaves = leaveRequestService.myLeave(username);
         log.info("API HIT: GET /api/leave/myLeave — Employee fetching details of there leave");
         return ResponseEntity.ok(leaves);
     }

    //    -------------------------------Approval for Leave Request----------------------------------
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/approve/{id}")
    public ResponseEntity<LeaveRequest> approveLeave(@PathVariable Long id)
     {
         LeaveRequest updated = leaveRequestService.approveLeave(id);
         log.info("API HIT: PATCH /api/leave/approve/{} — Admin approving the leave id ", id);
         return ResponseEntity.ok(updated);
     }

    //    -------------------------------Rejection for Leave Request----------------------------------
     @PreAuthorize("hasRole('ADMIN')")
     @PatchMapping("/reject/{id}")
     public ResponseEntity<LeaveRequest> rejectLeave(@PathVariable Long id)
     {
         LeaveRequest updated = leaveRequestService.rejectLeave(id);
         log.info("API HIT: PATCH /api/leave/reject/{} — Admin rejecting the leave id ", id);
         return ResponseEntity.ok(updated);
     }

    //    -------------------------------List of Leave Request----------------------------------
     @PreAuthorize("hasRole('ADMIN')")
     @GetMapping("/allLeaveRequest")
     public ResponseEntity<List<LeaveRequestAdminViewDTO>> getAllLeaveRequest()
    {
        List<LeaveRequestAdminViewDTO> allRequest = leaveRequestService.allLeaveRequest();
        log.info("API HIT: GET /api/leave/allLeaveRequest — Admin fetching all leave Request");
        return ResponseEntity.ok(allRequest);
    }

    //    -------------------------------List of Leave Request Pending----------------------------------
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pendingLeaveRequest")
    public ResponseEntity<List<LeaveRequestAdminViewDTO>> getPendingLeaveRequest()
    {
        List<LeaveRequestAdminViewDTO> allPendingRequest = leaveRequestService.allPendingLeaveRequest();
        log.info("API HIT: GET /api/leave/pendingLeaveRequest — Admin fetching all pending leave Request");
        return ResponseEntity.ok(allPendingRequest);
    }



}
