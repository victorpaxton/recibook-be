package com.hcmut.recibook.service;

import com.hcmut.recibook.model.dto.Ingredient.CategoryCreateDTO;
import com.hcmut.recibook.model.dto.Ingredient.IngredientCreateDTO;
import com.hcmut.recibook.model.dto.Response.PageResponse;
import com.hcmut.recibook.model.entity.Ingredient.Ingredient;
import com.hcmut.recibook.model.entity.Ingredient.IngredientCategory;

import java.util.List;
import java.util.UUID;

public interface IIngredientCategoryService {
    public List<IngredientCategory> getCategories();

    public IngredientCategory getCategory(UUID categoryId);

    public IngredientCategory addCategory(CategoryCreateDTO categoryCreateDTO);

    public Ingredient addByCategory(UUID categoryId, IngredientCreateDTO ingredientCreateDTO);

    public IngredientCategory updateCategory(UUID categoryId, CategoryCreateDTO categoryCreateDTO);

    public void deleteCategory(UUID categoryId);

    public PageResponse<Ingredient> getIngredientsByCategory(UUID categoryId, int pageNumber, int pageSize);
}
