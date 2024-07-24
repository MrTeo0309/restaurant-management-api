package com.res.main.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "transactions", schema = "db_restaurant_management", catalog = "")
public class TransactionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "amount")
    private Double amount;
    @Basic
    @Column(name = "pay_method")
    private String payMethod;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomersEntity  customer;
}
