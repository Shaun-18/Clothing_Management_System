package com.example.clothing_management.DTO;

import com.example.clothing_management.Entity.ClothingType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ClothingResponse(
        long id,
        String clothingId,
        String clothingItem,
        LocalDate dateAdded,
        ClothingType type,
        String brand,
        String store,
        BigDecimal price,
        String colour
) {
}

