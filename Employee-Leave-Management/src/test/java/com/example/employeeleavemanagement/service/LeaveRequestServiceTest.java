package com.example.employeeleavemanagement.service;

import com.example.employeeleavemanagement.Exception.LeaveRequestNotFoundException;
import com.example.employeeleavemanagement.dao.LeaveRequestRepository;
import com.example.employeeleavemanagement.model.LeaveRequest;
import com.example.employeeleavemanagement.model.LeaveStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LeaveRequestServiceTest {

    @Mock
    private LeaveRequestRepository leaveRequestRepository;

    @InjectMocks
    private LeaveRequestService leaveRequestService;

    @Test
    public void approveLeave_whenPending_shouldApprove()
    {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setId(1L);
        leaveRequest.setStatus(LeaveStatus.PENDING);

        when(leaveRequestRepository.findById(1L)).thenReturn(Optional.of(leaveRequest));
        when(leaveRequestRepository.save(leaveRequest)).thenReturn(leaveRequest);

        LeaveRequest updated = leaveRequestService.approveLeave(1L);

        assertEquals(LeaveStatus.APPROVED, updated.getStatus());
    }

    @Test
    public void approveLeave_whenNotPending_shouldThrow()
    {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setId(1L);
        leaveRequest.setStatus(LeaveStatus.APPROVED);
        when(leaveRequestRepository.findById(1L)).thenReturn(Optional.of(leaveRequest));
        assertThrows(LeaveRequestNotFoundException.class, () -> leaveRequestService.approveLeave(1L));
    }

    @Test
    public  void  approveLeave_whenNotFound_shouldThrow()
    {
        LeaveRequest leaveRequest = new LeaveRequest();
        when(leaveRequestRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(LeaveRequestNotFoundException.class, () -> leaveRequestService.approveLeave(2L));
    }

    @Test
    public void rejectLeave_whenPending_shouldReject()
    {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setId(1L);
        leaveRequest.setStatus(LeaveStatus.PENDING);

        when(leaveRequestRepository.findById(1L)).thenReturn(Optional.of(leaveRequest));
        when(leaveRequestRepository.save(leaveRequest)).thenReturn(leaveRequest);

        LeaveRequest updated = leaveRequestService.rejectLeave(1L);

        assertEquals(LeaveStatus.REJECTED, updated.getStatus());
    }

    @Test
    public void rejectLeave_whenNotPending_shouldThrow()
    {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setId(1L);
        leaveRequest.setStatus(LeaveStatus.REJECTED);
        when(leaveRequestRepository.findById(1L)).thenReturn(Optional.of(leaveRequest));
        assertThrows(LeaveRequestNotFoundException.class, () -> leaveRequestService.rejectLeave(1L));
        verify(leaveRequestRepository).findById(1L);
    }

    @Test
    public  void  rejectLeave_whenNotFound_shouldThrow()
    {
        LeaveRequest leaveRequest = new LeaveRequest();
        when(leaveRequestRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(LeaveRequestNotFoundException.class, () -> leaveRequestService.rejectLeave(2L));
    }



}
