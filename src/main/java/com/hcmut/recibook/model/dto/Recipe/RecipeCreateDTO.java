package com.hcmut.recibook.model.dto.Recipe;

import lombok.Data;

@Data
public class RecipeCreateDTO {
    private String recipeName;

    private String description;

    private int cookingTime;
}
