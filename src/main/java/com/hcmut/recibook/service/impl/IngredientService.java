package com.hcmut.recibook.service.impl;

import com.hcmut.recibook.file.CloudinaryService;
import com.hcmut.recibook.model.dto.Ingredient.IngredientCreateDTO;
import com.hcmut.recibook.model.dto.Response.PageResponse;
import com.hcmut.recibook.model.entity.Ingredient.Ingredient;
import com.hcmut.recibook.repository.Ingredient.IngredientRepository;
import com.hcmut.recibook.service.IIngredientService;
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
public class IngredientService implements IIngredientService {

    @Autowired
    private IngredientRepository recipeRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PageResponse<Ingredient> getIngredients(String keyword, int pageNumber, int pageSize, String sortField) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortField).ascending());

        return new PageResponse<>(recipeRepository.findPageIngredients(keyword, pageable));
    }

    @Override
    public Ingredient getIngredient(UUID ingredientId) {
        return recipeRepository.findById(ingredientId)
                .orElseThrow();
    }

    @Override
    public Ingredient addIngredient(IngredientCreateDTO ingredientCreateDTO) {
        Ingredient newIngredient = Ingredient.builder()
                .ingredientName(ingredientCreateDTO.getIngredientName())
                .description(ingredientCreateDTO.getDescription())
                .build();

        return recipeRepository.save(newIngredient);
    }

    @Override
    public Ingredient updateIngredient(UUID ingredientId, IngredientCreateDTO ingredientCreateDTO) {
        Ingredient ingredient = recipeRepository.findById(ingredientId)
                .orElseThrow();

        ingredient.setIngredientName(ingredientCreateDTO.getIngredientName());
        ingredient.setDescription(ingredientCreateDTO.getDescription());

        return recipeRepository.save(ingredient);
    }

    @Override
    public void deleteIngredient(UUID ingredientId) {
        recipeRepository.deleteById(ingredientId);
    }

    @Override
    public Ingredient uploadImage(UUID ingredientId, MultipartFile image) throws IOException {
        Ingredient ingredient = recipeRepository.findById(ingredientId)
                .orElseThrow();

        ingredient.setImage(cloudinaryService.uploadFile("ingredients", image));

        return recipeRepository.save(ingredient);
    }
}
