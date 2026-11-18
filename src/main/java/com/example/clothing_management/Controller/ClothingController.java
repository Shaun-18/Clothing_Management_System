
package com.example.clothing_management.Controller;

import com.example.clothing_management.DTO.ClothesRequest;
import com.example.clothing_management.DTO.ClothingResponse;
import com.example.clothing_management.Service.ClothingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Clothing")
public class ClothingController {

    private final ClothingService clothingService;

    public ClothingController(ClothingService clothingService) {
        this.clothingService = clothingService;
    }

    // Create Clothing
    @PostMapping
    public ResponseEntity<ClothingResponse> createClothing(
            @Valid @RequestBody ClothesRequest request) {

        ClothingResponse response = clothingService.createClothing(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get All Clothing
    @GetMapping
    public ResponseEntity<List<ClothingResponse>> getAllClothing() {

        return ResponseEntity.ok(clothingService.getAllClothing());
    }

    // Get Clothing By ID
    @GetMapping("/{id}")
    public ResponseEntity<ClothingResponse> getClothingById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                clothingService.getClothingById(id)
        );
    }

    // Update Clothing
    @PutMapping("/{id}")
    public ResponseEntity<ClothingResponse> updateClothing(
            @PathVariable Long id,
            @Valid @RequestBody ClothesRequest request) {

        return ResponseEntity.ok(
                clothingService.updateClothing(id, request)
        );
    }

    // Delete Clothing
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClothing(
            @PathVariable Long id) {

        clothingService.deleteClothing(id);

        return ResponseEntity.noContent().build();
    }
}

