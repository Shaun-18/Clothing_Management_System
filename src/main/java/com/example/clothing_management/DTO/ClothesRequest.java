

package com.example.clothing_management.DTO;

import com.example.clothing_management.Entity.ClothingType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ClothesRequest(

        @NotBlank(message = "Clothing ID is required")
        @Size(max = 10, message = "Clothing ID must not exceed 10 characters")
        String clothingId,

        @NotBlank(message = "Clothing Item is required")
        @Size(max = 250, message = "Clothing item must not exceed 250 characters")
        String clothingItem,

        @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull(message = "Date is required")
        LocalDate dateAdded,

        @NotNull(message = "Clothing Type is required")
        ClothingType type,

        @NotBlank(message = "Brand is required")
        @Size(max = 250, message = "Brand must not exceed 250 characters")
        String brand,

        @NotBlank(message = "Store is required")
        @Size(max = 250, message = "Store must not exceed 250 characters")
        String store,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.01", message = "Price must be greater than zero")
        BigDecimal price,

        @NotBlank(message = "Colour is required")
        @Size(max = 50, message = "Colour must not exceed 50 characters")
        String colour

) {
}