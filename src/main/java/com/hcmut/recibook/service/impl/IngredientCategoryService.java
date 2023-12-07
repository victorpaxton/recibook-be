package com.hcmut.recibook.service.impl;

import com.hcmut.recibook.model.dto.Ingredient.CategoryCreateDTO;
import com.hcmut.recibook.model.dto.Ingredient.IngredientCreateDTO;
import com.hcmut.recibook.model.dto.Response.PageResponse;
import com.hcmut.recibook.model.entity.Ingredient.Ingredient;
import com.hcmut.recibook.model.entity.Ingredient.IngredientCategory;
import com.hcmut.recibook.repository.Ingredient.IngredientCategoryRepository;
import com.hcmut.recibook.repository.Ingredient.IngredientRepository;
import com.hcmut.recibook.service.IIngredientCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IngredientCategoryService implements IIngredientCategoryService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Override
    public List<IngredientCategory> getCategories() {
        return ingredientCategoryRepository.findAll();
    }

    @Override
    public IngredientCategory getCategory(UUID categoryId) {
        return ingredientCategoryRepository.findById(categoryId)
                .orElseThrow();
    }

    @Override
    public IngredientCategory addCategory(CategoryCreateDTO categoryCreateDTO) {
        IngredientCategory newCategory = IngredientCategory.builder()
                .ingredientCategoryName(categoryCreateDTO.getCategoryName())
                .description(categoryCreateDTO.getDescription())
                .build();

        return ingredientCategoryRepository.save(newCategory);
    }

    @Override
    public Ingredient addByCategory(UUID categoryId, IngredientCreateDTO ingredientCreateDTO) {
        Ingredient newIngredient = Ingredient.builder()
                .ingredientName(ingredientCreateDTO.getIngredientName())
                .description(ingredientCreateDTO.getDescription())
                .ingredientCategory(ingredientCategoryRepository.findById(categoryId).orElseThrow())
                .build();
        
        return ingredientRepository.save(newIngredient);
    }

    @Override
    public IngredientCategory updateCategory(UUID categoryId, CategoryCreateDTO categoryCreateDTO) {
        IngredientCategory category = ingredientCategoryRepository.findById(categoryId)
                .orElseThrow();

        category.setIngredientCategoryName(categoryCreateDTO.getCategoryName());
        category.setDescription(categoryCreateDTO.getDescription());

        return ingredientCategoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID categoryId) {
        ingredientCategoryRepository.deleteById(categoryId);
    }

    @Override
    public PageResponse<Ingredient> getIngredientsByCategory(UUID categoryId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("ingredient_name").ascending());

        return new PageResponse<>(ingredientRepository.findByCategory(categoryId, pageable));
    }
}
