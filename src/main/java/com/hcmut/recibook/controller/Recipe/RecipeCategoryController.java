package com.hcmut.recibook.controller.Recipe;

import com.hcmut.recibook.model.dto.Ingredient.CategoryCreateDTO;
import com.hcmut.recibook.model.dto.Recipe.RecipeCreateDTO;
import com.hcmut.recibook.model.dto.Response.ResponseModel;
import com.hcmut.recibook.service.IRecipeCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/recipe-categories")
public class RecipeCategoryController {

    @Autowired
    private IRecipeCategoryService recipeCategoryService;

    @GetMapping
    @Operation(summary = "Get all recipe categories")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> getCategories() {
        return ResponseModel.builder()
                .isSuccess(true)
                .data(recipeCategoryService.getCategories())
                .errors(null)
                .build();

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a detailed recipe category")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> getCategory(@PathVariable("id") UUID categoryId) {
        return ResponseModel.builder()
                .isSuccess(true)
                .data(recipeCategoryService.getCategory(categoryId))
                .errors(null)
                .build();

    }

    @PostMapping
    @Operation(summary = "Add a new category")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> addCategory(@RequestBody CategoryCreateDTO categoryCreateDTO) {
        return ResponseModel.builder()
                .isSuccess(true)
                .data(recipeCategoryService.addCategory(categoryCreateDTO))
                .errors(null)
                .build();

    }

    @PutMapping("{id}")
    @Operation(summary = "Update an category")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> updateCategory(@PathVariable("id") UUID categoryId, @RequestBody CategoryCreateDTO categoryCreateDTO) {
        return ResponseModel.builder()
                .isSuccess(true)
                .data(recipeCategoryService.updateCategory(categoryId, categoryCreateDTO))
                .errors(null)
                .build();

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an category")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> deleteRecipe(@PathVariable("id") UUID categoryId) {
        recipeCategoryService.deleteCategory(categoryId);

        return ResponseModel.builder()
                .isSuccess(true)
                .data("Delete recipe successfully")
                .errors(null)
                .build();
    }

    @GetMapping("/{id}/recipes")
    @Operation(summary = "Get all recipes of a category")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> getRecipesByCategory(@PathVariable("id") UUID categoryId,
                                                          @RequestParam(defaultValue = "0") int pageNumber,
                                                          @RequestParam(defaultValue = "4") int pageSize) {

        return ResponseModel.builder()
                .isSuccess(true)
                .data(recipeCategoryService.getRecipesByCategory(categoryId, pageNumber, pageSize))
                .errors(null)
                .build();
    }

    @PostMapping("{id}")
    @Operation(summary = "Add an recipe to a category")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> addByCategory(@PathVariable("id") UUID categoryId, @RequestBody RecipeCreateDTO recipeCreateDTO) {
        return ResponseModel.builder()
                .isSuccess(true)
                .data(recipeCategoryService.addByCategory(categoryId, recipeCreateDTO))
                .errors(null)
                .build();
    }

}
