package com.hcmut.recibook.controller.Recipe;

import com.hcmut.recibook.model.dto.Ingredient.RecipeIngredientDTO;
import com.hcmut.recibook.model.dto.Recipe.RecipeCreateDTO;
import com.hcmut.recibook.model.dto.Response.ResponseModel;
import com.hcmut.recibook.service.IRecipeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private IRecipeService recipeService;

    @GetMapping
    @Operation(summary = "Get all recipes with pagination")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> getRecipes(@RequestParam(defaultValue = "") String keyword,
                                                @RequestParam(defaultValue = "0") int pageNumber,
                                                @RequestParam(defaultValue = "4") int pageSize,
                                                @RequestParam(defaultValue = "id") String sortField) {
        return ResponseModel.builder()
                .isSuccess(true)
                .data(recipeService.getRecipes(keyword, pageNumber, pageSize, sortField))
                .errors(null)
                .build();

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a detailed recipe")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> getRecipe(@PathVariable("id") UUID recipeId) {
        return ResponseModel.builder()
                .isSuccess(true)
                .data(recipeService.getRecipe(recipeId))
                .errors(null)
                .build();

    }

    @PostMapping("/{id}/ingredients")
    @Operation(summary = "Add ingredients for a recipe")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> addIngredientsByRecipe(@PathVariable("id") UUID recipeId,
                                                        @RequestBody RecipeIngredientDTO recipeIngredientDTO) {
        return ResponseModel.builder()
                .isSuccess(true)
                .data(recipeService.addIngredientsByRecipe(recipeId, recipeIngredientDTO))
                .errors(null)
                .build();

    }

    @PostMapping
    @Operation(summary = "Add a new recipe")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> addRecipe(@RequestBody RecipeCreateDTO recipeCreateDTO) {
        return ResponseModel.builder()
                .isSuccess(true)
                .data(recipeService.addRecipe(recipeCreateDTO))
                .errors(null)
                .build();

    }

    @PutMapping("{id}")
    @Operation(summary = "Update an recipe")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> updateRecipe(@PathVariable("id") UUID recipeId, @RequestBody RecipeCreateDTO recipeCreateDTO) {
        return ResponseModel.builder()
                .isSuccess(true)
                .data(recipeService.updateRecipe(recipeId, recipeCreateDTO))
                .errors(null)
                .build();

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an recipe")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> deleteRecipe(@PathVariable("id") UUID recipeId) {
        recipeService.deleteRecipe(recipeId);

        return ResponseModel.builder()
                .isSuccess(true)
                .data("Delete recipe successfully")
                .errors(null)
                .build();
    }

    @PutMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload image for recipe")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> uploadImage(@PathVariable("id") UUID recipeId, @RequestPart MultipartFile image) throws IOException {

        return ResponseModel.builder()
                .isSuccess(true)
                .data(recipeService.uploadImage(recipeId, image))
                .errors(null)
                .build();
    }

}
