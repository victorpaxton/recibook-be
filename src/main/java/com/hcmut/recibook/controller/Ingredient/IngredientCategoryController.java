package com.hcmut.recibook.controller.Ingredient;

import com.hcmut.recibook.model.dto.Ingredient.CategoryCreateDTO;
import com.hcmut.recibook.model.dto.Ingredient.IngredientCreateDTO;
import com.hcmut.recibook.model.dto.Response.ResponseModel;
import com.hcmut.recibook.service.IIngredientCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/ingredient-categories")
public class IngredientCategoryController {

    @Autowired
    private IIngredientCategoryService ingredientCategoryService;

    @GetMapping
    @Operation(summary = "Get all ingredient categories")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> getCategories() {
        return ResponseModel.builder()
                .isSuccess(true)
                .data(ingredientCategoryService.getCategories())
                .errors(null)
                .build();

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a detailed ingredient category")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> getCategory(@PathVariable("id") UUID categoryId) {
        return ResponseModel.builder()
                .isSuccess(true)
                .data(ingredientCategoryService.getCategory(categoryId))
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
                .data(ingredientCategoryService.addCategory(categoryCreateDTO))
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
                .data(ingredientCategoryService.updateCategory(categoryId, categoryCreateDTO))
                .errors(null)
                .build();

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an category")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> deleteIngredient(@PathVariable("id") UUID categoryId) {
        ingredientCategoryService.deleteCategory(categoryId);

        return ResponseModel.builder()
                .isSuccess(true)
                .data("Delete ingredient successfully")
                .errors(null)
                .build();
    }

    @GetMapping("/{id}/ingredients")
    @Operation(summary = "Get all ingredients of a category")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> getIngredientsByCategory(@PathVariable("id") UUID categoryId,
                                                          @RequestParam(defaultValue = "0") int pageNumber,
                                                          @RequestParam(defaultValue = "4") int pageSize) {

        return ResponseModel.builder()
                .isSuccess(true)
                .data(ingredientCategoryService.getIngredientsByCategory(categoryId, pageNumber, pageSize))
                .errors(null)
                .build();
    }

    @PostMapping("{id}")
    @Operation(summary = "Add an ingredient to a category")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> addByCategory(@PathVariable("id") UUID categoryId, @RequestBody IngredientCreateDTO ingredientCreateDTO) {
        return ResponseModel.builder()
                .isSuccess(true)
                .data(ingredientCategoryService.addByCategory(categoryId, ingredientCreateDTO))
                .errors(null)
                .build();
    }

}
