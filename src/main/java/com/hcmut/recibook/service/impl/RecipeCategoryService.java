package com.hcmut.recibook.service.impl;

import com.hcmut.recibook.model.dto.Ingredient.CategoryCreateDTO;
import com.hcmut.recibook.model.dto.Recipe.RecipeBasicDTO;
import com.hcmut.recibook.model.dto.Recipe.RecipeCreateDTO;
import com.hcmut.recibook.model.dto.Response.PageResponse;
import com.hcmut.recibook.model.entity.Ingredient.Ingredient;
import com.hcmut.recibook.model.entity.Recipe.Recipe;
import com.hcmut.recibook.model.entity.Recipe.RecipeCategory;
import com.hcmut.recibook.model.entity.Recipe.RecipeIngredient;
import com.hcmut.recibook.repository.Recipe.RecipeCategoryRepository;
import com.hcmut.recibook.repository.Recipe.RecipeRepository;
import com.hcmut.recibook.service.IRecipeCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecipeCategoryService implements IRecipeCategoryService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeCategoryRepository recipeCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RecipeCategory> getCategories() {
        return recipeCategoryRepository.findAll();
    }

    @Override
    public RecipeCategory getCategory(UUID categoryId) {
        return recipeCategoryRepository.findById(categoryId)
                .orElseThrow();
    }

    @Override
    public RecipeCategory addCategory(CategoryCreateDTO categoryCreateDTO) {
        RecipeCategory newCategory = RecipeCategory.builder()
                .recipeCategoryName(categoryCreateDTO.getCategoryName())
                .description(categoryCreateDTO.getDescription())
                .build();

        return recipeCategoryRepository.save(newCategory);
    }

    @Override
    public Recipe addByCategory(UUID categoryId, RecipeCreateDTO recipeCreateDTO) {

        Recipe newRecipe = modelMapper.map(recipeCreateDTO, Recipe.class);

        return recipeRepository.save(newRecipe);
    }

    @Override
    public RecipeCategory updateCategory(UUID categoryId, CategoryCreateDTO categoryCreateDTO) {
        RecipeCategory category = recipeCategoryRepository.findById(categoryId)
                .orElseThrow();

        category.setRecipeCategoryName(categoryCreateDTO.getCategoryName());
        category.setDescription(categoryCreateDTO.getDescription());

        return recipeCategoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID categoryId) {
        recipeCategoryRepository.deleteById(categoryId);
    }

    @Override
    public PageResponse<RecipeBasicDTO> getRecipesByCategory(UUID categoryId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("recipe_name").ascending());

        return new PageResponse<>(recipeRepository.findByRecipeCategoryId(categoryId, pageable)
                .map(recipe -> modelMapper.map(recipe, RecipeBasicDTO.class)));
    }
}
