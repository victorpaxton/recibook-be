package com.hcmut.recibook.repository.Ingredient;

import com.hcmut.recibook.model.entity.Ingredient.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {

    @Query(value = "SELECT * FROM ingredient AS ing " +
            "WHERE CONCAT(ing.ingredient_name, ' ', ing.description, ' ', ing.image) " +
            "LIKE %:keyword%", nativeQuery = true)
    Page<Ingredient> findPageIngredients(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "select * from ingredient where category_id = :categoryId",
            countQuery = "select count(*) from ingredient where category_id = :categoryId",
            nativeQuery = true)
    Page<Ingredient> findByCategory(@Param("categoryId") UUID categoryId, Pageable pageable);
}
