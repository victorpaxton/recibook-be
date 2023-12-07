package com.hcmut.recibook.controller.Ingredient;

import com.hcmut.recibook.model.dto.Ingredient.IngredientCreateDTO;
import com.hcmut.recibook.model.dto.Response.ResponseModel;
import com.hcmut.recibook.service.IIngredientService;
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
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IIngredientService ingredientService;

    @GetMapping
    @Operation(summary = "Get all ingredients with pagination")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> getIngredients(@RequestParam(defaultValue = "") String keyword,
                                                @RequestParam(defaultValue = "0") int pageNumber,
                                                @RequestParam(defaultValue = "4") int pageSize,
                                                @RequestParam(defaultValue = "id") String sortField) {
        return ResponseModel.builder()
                .isSuccess(true)
                .data(ingredientService.getIngredients(keyword, pageNumber, pageSize, sortField))
                .errors(null)
                .build();

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a detailed ingredient")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> getIngredient(@PathVariable("id") UUID ingredientId) {
        return ResponseModel.builder()
                .isSuccess(true)
                .data(ingredientService.getIngredient(ingredientId))
                .errors(null)
                .build();

    }

    @PostMapping
    @Operation(summary = "Add a new ingredient")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> addIngredient(@RequestBody IngredientCreateDTO ingredientCreateDTO) {
        return ResponseModel.builder()
                .isSuccess(true)
                .data(ingredientService.addIngredient(ingredientCreateDTO))
                .errors(null)
                .build();

    }

    @PutMapping("{id}")
    @Operation(summary = "Update an ingredient")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> updateIngredient(@PathVariable("id") UUID ingredientId, @RequestBody IngredientCreateDTO ingredientCreateDTO) {
        return ResponseModel.builder()
                .isSuccess(true)
                .data(ingredientService.updateIngredient(ingredientId, ingredientCreateDTO))
                .errors(null)
                .build();

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an ingredient")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> deleteIngredient(@PathVariable("id") UUID ingredientId) {
        ingredientService.deleteIngredient(ingredientId);

        return ResponseModel.builder()
                .isSuccess(true)
                .data("Delete ingredient successfully")
                .errors(null)
                .build();
    }

    @PutMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload image for ingredient")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> uploadImage(@PathVariable("id") UUID ingredientId, @RequestPart MultipartFile image) throws IOException {

        return ResponseModel.builder()
                .isSuccess(true)
                .data(ingredientService.uploadImage(ingredientId, image))
                .errors(null)
                .build();
    }

}
