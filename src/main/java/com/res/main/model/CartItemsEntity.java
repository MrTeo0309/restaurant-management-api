package com.res.main.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "cart_items", schema = "db_restaurant_management", catalog = "")
public class CartItemsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "is_selected")
    private Boolean isSelected;
    @ManyToOne
    @JoinColumn(name = "dish_id")
    private DishesEntity dish;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartEntity cart;
}
