package com.res.main.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
@Data
@Entity
@Table(name = "orders", schema = "db_restaurant_management", catalog = "")
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "total")
    private Double total;
    @Basic
    @Column(name = "note")
    private String note;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private TablesEntity table;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomersEntity customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeesEntity employee;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private DiscountsEntity discount;

    @ManyToOne
    @JoinColumn(name = "rate_id")
    private RateEntity rate;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private InvoiceEntity invoice;
}
