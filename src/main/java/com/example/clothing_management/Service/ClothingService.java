
package com.example.clothing_management.Service;

import com.example.clothing_management.DTO.ClothesRequest;
import com.example.clothing_management.DTO.ClothingResponse;
import com.example.clothing_management.Entity.Clothing;
import com.example.clothing_management.Exceptions.DuplicateResourceException;
import com.example.clothing_management.Exceptions.ResourceNotFoundException;
import com.example.clothing_management.Repository.ClothingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClothingService {

    private final ClothingRepository clothingRepository;

    public ClothingService(ClothingRepository clothingRepository) {
        this.clothingRepository = clothingRepository;
    }

    // Create Clothing
    @Transactional
    public ClothingResponse createClothing(ClothesRequest request) {

        if (clothingRepository.existsByClothingId(request.clothingId())) {
            throw new DuplicateResourceException(
                    "Clothing with this ID already exists"
            );
        }

        Clothing clothing = new Clothing();

        clothing.setClothingId(request.clothingId());
        clothing.setClothingItem(request.clothingItem());
        clothing.setDateAdded(request.dateAdded());
        clothing.setType(request.type());
        clothing.setBrand(request.brand());
        clothing.setColour(request.colour());
        clothing.setPrice(request.price());
        clothing.setStore(request.store());


        Clothing savedClothing = clothingRepository.save(clothing);

        return mapToResponse(savedClothing);
    }

    // Read All Clothing
    @Transactional(readOnly = true)
    public List<ClothingResponse> getAllClothing() {

        return clothingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Read One Clothing Item
    @Transactional(readOnly = true)
    public ClothingResponse getClothingById(long id) {

        Clothing clothing = findClothingOrThrow(id);

        return mapToResponse(clothing);
    }

    // Update Clothing
    @Transactional
    public ClothingResponse updateClothing(long id, ClothesRequest request) {

        Clothing clothing = findClothingOrThrow(id);

        boolean clothingAlreadyExists =
                clothingRepository.existsByClothingIdAndIdNot(
                        request.clothingId(), id
                );

        if (clothingAlreadyExists) {
            throw new DuplicateResourceException(
                    "Clothing with this ID already exists"
            );
        }

        clothing.setClothingId(request.clothingId());
        clothing.setClothingItem(request.clothingItem());
        clothing.setDateAdded(request.dateAdded());
        clothing.setType(request.type());
        clothing.setBrand(request.brand());
        clothing.setColour(request.colour());
        clothing.setPrice(request.price());
        clothing.setStore(request.store());

        Clothing updatedClothing = clothingRepository.save(clothing);

        return mapToResponse(updatedClothing);
    }

    // Delete Clothing
    @Transactional
    public void deleteClothing(long id) {

        Clothing clothing = findClothingOrThrow(id);

        clothingRepository.delete(clothing);
    }

    // Find Clothing or Throw Exception
    private Clothing findClothingOrThrow(long id) {

        return clothingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Clothing not found with ID: " + id
                ));
    }

    // Map Entity to Response DTO
    private ClothingResponse mapToResponse(Clothing clothing) {

        return new ClothingResponse(
                clothing.getId(),
                clothing.getClothingId(),
                clothing.getClothingItem(),
                clothing.getDateAdded(),
                clothing.getType(),
                clothing.getBrand(),
                clothing.getStore(),
                clothing.getPrice(),
                clothing.getColour()
        );
    }
}

