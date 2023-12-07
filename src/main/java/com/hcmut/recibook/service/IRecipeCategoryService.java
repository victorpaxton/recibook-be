package com.hcmut.recibook.service;

import com.hcmut.recibook.model.dto.Ingredient.CategoryCreateDTO;
import com.hcmut.recibook.model.dto.Recipe.RecipeBasicDTO;
import com.hcmut.recibook.model.dto.Recipe.RecipeCreateDTO;
import com.hcmut.recibook.model.dto.Response.PageResponse;
import com.hcmut.recibook.model.entity.Recipe.Recipe;
import com.hcmut.recibook.model.entity.Recipe.RecipeCategory;

import java.util.List;
import java.util.UUID;

public interface IRecipeCategoryService {
    public List<RecipeCategory> getCategories();

    public RecipeCategory getCategory(UUID categoryId);

    public RecipeCategory addCategory(CategoryCreateDTO categoryCreateDTO);

    public Recipe addByCategory(UUID categoryId, RecipeCreateDTO recipeCreateDTO);

    public RecipeCategory updateCategory(UUID categoryId, CategoryCreateDTO categoryCreateDTO);

    public void deleteCategory(UUID categoryId);

    public PageResponse<RecipeBasicDTO> getRecipesByCategory(UUID categoryId, int pageNumber, int pageSize);
}
