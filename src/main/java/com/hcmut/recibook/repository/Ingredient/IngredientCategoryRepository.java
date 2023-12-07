package com.hcmut.recibook.repository.Ingredient;

import com.hcmut.recibook.model.entity.Ingredient.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, UUID> {

}
