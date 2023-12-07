package com.hcmut.recibook.service;

import com.hcmut.recibook.model.dto.Ingredient.IngredientCreateDTO;
import com.hcmut.recibook.model.dto.Response.PageResponse;
import com.hcmut.recibook.model.entity.Ingredient.Ingredient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface IIngredientService {
    public PageResponse<Ingredient> getIngredients(String keyword, int pageNumber, int pageSize, String sortField);

    public Ingredient getIngredient(UUID ingredientId);

    public Ingredient addIngredient(IngredientCreateDTO ingredientCreateDTO);

    public Ingredient updateIngredient(UUID ingredientId, IngredientCreateDTO ingredientCreateDTO);

    public void deleteIngredient(UUID ingredientId);

    public Ingredient uploadImage(UUID ingredientId, MultipartFile image) throws IOException;
}
