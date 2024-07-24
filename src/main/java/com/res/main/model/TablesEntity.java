package com.res.main.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tables", schema = "db_restaurant_management", catalog = "")
public class TablesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "number")
    private Integer number;
    @Basic
    @Column(name = "seat")
    private Integer seat;
    @Basic
    @Column(name = "status")
    private String status;
}
