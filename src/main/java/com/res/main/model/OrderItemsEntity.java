package com.res.main.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_items", schema = "db_restaurant_management", catalog = "")
public class OrderItemsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "order_id")
    private Long orderId;
    @Basic
    @Column(name = "dish_id")
    private Long dishId;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
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
