package com.res.main.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "suppliers", schema = "db_restaurant_management", catalog = "")
public class SuppliersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @NotBlank(message = "Name cannot be empty")
    @Column(name = "name")
    private String name;
    @Basic
    @NotBlank(message = "Contact cannot be empty")
    @Column(name = "contact")
    private String contact;
    @Basic
    @NotBlank(message = "Address cannot be empty")
    @Column(name = "address")
    private String address;
}
