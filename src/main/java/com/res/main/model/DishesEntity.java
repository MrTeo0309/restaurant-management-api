package com.res.main.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "dishes", schema = "db_restaurant_management", catalog = "")
public class DishesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @NotBlank(message = "Dish name cannot be empty")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Description cannot be empty")
    @Column(name = "description")
    private String description;

    @NotBlank(message = "Price cannot be empty")
    @Column(name = "price")
    private Double price;

    @NotBlank(message = "Image cannot be empty")
    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoriesEntity category;

    @OneToOne(mappedBy = "dish")
    private MenuItemsEntity menuItem;

    @OneToOne(mappedBy = "dish")
    private OrderItemsEntity orderItem;
}
