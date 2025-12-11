package com.example.employeeleavemanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "department")
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("department name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    List<Employee> employees;
}
