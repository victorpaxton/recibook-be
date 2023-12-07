package com.hcmut.recibook.model.dto.Ingredient;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class RecipeIngredientDTO {
    private List<IngredientInRecipe> ingredients;

    @Data
    public static class IngredientInRecipe {
        UUID ingredientId;
        int amount;
    }
}
