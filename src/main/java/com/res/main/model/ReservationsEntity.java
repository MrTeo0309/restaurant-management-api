package com.res.main.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
@Data
@Entity
@Table(name = "reservations", schema = "db_restaurant_management", catalog = "")
public class ReservationsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "date")
    private Timestamp date;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private TablesEntity table;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomersEntity customer;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeesEntity employee;
}
