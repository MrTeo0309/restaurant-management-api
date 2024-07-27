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

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrdersEntity order;

    @OneToOne
    @JoinColumn(name = "dish_id")
    private DishesEntity dish;

    @Basic
    @Column(name = "quantity")
    private Integer quantity;
}
