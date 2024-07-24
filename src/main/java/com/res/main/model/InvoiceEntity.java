package com.res.main.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "invoice", schema = "db_restaurant_management", catalog = "")
public class InvoiceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "total")
    private Double total;
    @Basic
    @Column(name = "payment_method")
    private String paymentMethod;
    @Basic
    @Column(name = "payment_date")
    private Date paymentDate;
    @OneToMany(mappedBy = "invoice")
    private List<OrdersEntity> orders;
}
