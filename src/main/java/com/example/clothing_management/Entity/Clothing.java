package com.example.clothing_management.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor



@Table(name = "clothing_items")
public class Clothing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ClothingId", nullable = false, unique = true)
    String clothingId;

    @Column(name = "ClothingItem", nullable = false)
    String clothingItem;

    @Column(name = "DateAdded", nullable = false)
    LocalDate dateAdded;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type", nullable = false)
    ClothingType type;

    @Column(name = "Brand", nullable = false)
    String brand;


    @Column(name = "Price" ,nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "Colour",nullable = false, length = 50)
    private String colour;


    @Column(name = "Store", nullable = false)
    String store;
}
