package com.res.main.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "employees", schema = "db_restaurant_management", catalog = "")
public class EmployeesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @NotBlank(message = "Name cannot be empty")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Phone number cannot be empty")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank(message = "Role cannot be empty")
    @Column(name = "role")
    private String role;

    @NotBlank(message = "Email cannot be empty")
    @Column(name = "email")
    private String email;
}

