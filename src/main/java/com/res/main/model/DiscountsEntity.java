package com.res.main.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "discounts", schema = "db_restaurant_management", catalog = "")
public class DiscountsEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @NotBlank(message = "Code cannot be blank")
    @Size(max = 8, message = "Code cannot exceed 8 characters")
    @Column(name = "code", unique = true)
    private String code;

    @NotNull(message = "Create date cannot be null")
    @PastOrPresent(message = "Create date must be in the past or present")
    @Column(name = "create_at")
    private Date createAt;

    @NotNull(message = "End date cannot be null")
    @Future(message = "End date must be in the future")
    @Column(name = "date_end")
    private Date dateEnd;

    @Min(value = 0, message = "Quantity must be zero or positive")
    @Column(name = "quantity")
    private Integer quantity;

    @DecimalMin(value = "0.0", message = "Quota must be greater than zero")
    @Column(name = "quota")
    private Double minPurchaseAmount;
}
