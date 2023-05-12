package com.paul.shoewearbackend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
}
