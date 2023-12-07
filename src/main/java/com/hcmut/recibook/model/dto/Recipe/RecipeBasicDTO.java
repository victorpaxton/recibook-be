package com.hcmut.recibook.model.dto.Recipe;

import com.hcmut.recibook.model.entity.Recipe.RecipeCategory;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.UUID;

@Data
public class RecipeBasicDTO {
    private UUID id;

    private String recipeName;

    private String description;

    private int cookingTime;

    private String image;

    private RecipeCategory recipeCategory;
}
