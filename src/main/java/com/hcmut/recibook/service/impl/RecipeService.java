package com.hcmut.recibook.service.impl;

import com.hcmut.recibook.file.CloudinaryService;
import com.hcmut.recibook.model.dto.Ingredient.RecipeIngredientDTO;
import com.hcmut.recibook.model.dto.Recipe.RecipeBasicDTO;
import com.hcmut.recibook.model.dto.Recipe.RecipeCreateDTO;
import com.hcmut.recibook.model.dto.Response.PageResponse;
import com.hcmut.recibook.model.entity.Recipe.Recipe;
import com.hcmut.recibook.model.entity.Recipe.RecipeIngredient;
import com.hcmut.recibook.repository.Ingredient.IngredientRepository;
import com.hcmut.recibook.repository.Recipe.RecipeIngredientRepository;
import com.hcmut.recibook.repository.Recipe.RecipeRepository;
import com.hcmut.recibook.service.IRecipeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class RecipeService implements IRecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PageResponse<RecipeBasicDTO> getRecipes(String keyword, int pageNumber, int pageSize, String sortField) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortField).ascending());

        return new PageResponse<>(recipeRepository.findPageRecipes(keyword, pageable).map(r -> modelMapper.map(r, RecipeBasicDTO.class)));
    }

    @Override
    public Recipe getRecipe(UUID recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow();
    }

    @Override
    public Recipe addRecipe(RecipeCreateDTO recipeCreateDTO) {
        Recipe newRecipe = Recipe.builder()
                .recipeName(recipeCreateDTO.getRecipeName())
                .description(recipeCreateDTO.getDescription())
                .direction(recipeCreateDTO.getDirection())
                .cookingTime(recipeCreateDTO.getCookingTime())
                .cuisine(recipeCreateDTO.getCuisine())
                .build();

        return recipeRepository.save(newRecipe);
    }

    @Override
    public Recipe updateRecipe(UUID recipeId, RecipeCreateDTO recipeCreateDTO) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow();

        recipe.setRecipeName(recipeCreateDTO.getRecipeName());
        recipe.setDescription(recipeCreateDTO.getDescription());
        recipe.setCookingTime(recipeCreateDTO.getCookingTime());
        recipe.setDirection(recipeCreateDTO.getDirection());
        recipe.setCuisine(recipeCreateDTO.getCuisine());

        return recipeRepository.save(recipe);
    }

    @Override
    public void deleteRecipe(UUID recipeId) {
        recipeRepository.deleteById(recipeId);
    }

    @Override
    public Recipe uploadImage(UUID recipeId, MultipartFile image) throws IOException {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow();

        recipe.setImage(cloudinaryService.uploadFile("recipes", image));

        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe addIngredientsByRecipe(UUID recipeId, RecipeIngredientDTO recipeIngredientDTO) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow();

        recipeIngredientDTO.getIngredients()
                .forEach(ingredient ->
                     recipeIngredientRepository.save(RecipeIngredient.builder()
                            .recipe(recipe)
                            .ingredient(ingredientRepository.findById(ingredient.getIngredientId()).orElseThrow())
                            .amount(ingredient.getAmount())
                             .unit(ingredient.getUnit())
                            .build())
                );

        return recipeRepository.save(recipe);
    }

}
