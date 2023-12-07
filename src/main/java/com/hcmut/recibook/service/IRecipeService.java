package com.hcmut.recibook.service;

import com.hcmut.recibook.model.dto.Ingredient.RecipeIngredientDTO;
import com.hcmut.recibook.model.dto.Recipe.RecipeBasicDTO;
import com.hcmut.recibook.model.dto.Recipe.RecipeCreateDTO;
import com.hcmut.recibook.model.dto.Response.PageResponse;
import com.hcmut.recibook.model.entity.Recipe.Recipe;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface IRecipeService {
    public PageResponse<RecipeBasicDTO> getRecipes(String keyword, int pageNumber, int pageSize, String sortField);

    public Recipe getRecipe(UUID recipeId);

    public Recipe addRecipe(RecipeCreateDTO recipeCreateDTO);

    public Recipe updateRecipe(UUID recipeId, RecipeCreateDTO recipeCreateDTO);

    public void deleteRecipe(UUID recipeId);

    public Recipe uploadImage(UUID recipeId, MultipartFile image) throws IOException;


    Recipe addIngredientsByRecipe(UUID recipeId, RecipeIngredientDTO recipeIngredientDTO);
}
