
package com.example.clothing_management.Repository;

import com.example.clothing_management.Entity.Clothing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClothingRepository extends JpaRepository<Clothing, Long> {


    boolean existsByClothingId(String clothingId);


    boolean existsByClothingIdAndIdNot(String clothingId, Long id);


    Optional<Clothing> findByClothingId(String clothingId);
}

