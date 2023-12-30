package com.hcmut.recibook.model.dto.Recommendation;

import com.hcmut.recibook.model.entity.Recipe.RecipeCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuggestRecipe {
    private UUID id;

    private String recipeName;

    private String description;

    private int cookingTime;

    private String image;

    private int missingIngredient;

    private RecipeCategory recipeCategory;
}
