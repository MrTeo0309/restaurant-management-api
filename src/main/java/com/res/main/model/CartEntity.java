package com.res.main.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "cart", schema = "db_restaurant_management")
public class CartEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomersEntity customer;
}
