package com.res.main.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "menu_items", schema = "db_restaurant_management", catalog = "")
public class MenuItemsEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @OneToOne
    @JoinColumn(name = "dish_id")
    private DishesEntity dish;

    @Column(name = "availability")
    private String availability;
}
