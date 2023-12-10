package com.hcmut.recibook.model.dto.Recipe;

import lombok.Data;

@Data
public class RecipeCreateDTO {
    private String recipeName;

    private String description;

    private String direction;

    private int cookingTime;

    private String image;

    private String cuisine;
}
