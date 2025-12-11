package com.example.employeeleavemanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private List<LeaveRequest> leaveRequests;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Users user;
}
