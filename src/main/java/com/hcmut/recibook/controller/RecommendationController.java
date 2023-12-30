package com.hcmut.recibook.controller;

import com.hcmut.recibook.model.dto.Recommendation.SuggestRequest;
import com.hcmut.recibook.model.dto.Response.ResponseModel;
import com.hcmut.recibook.model.entity.Ingredient.Ingredient;
import com.hcmut.recibook.service.IRecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping()
@Tag(name = "Recommendation Controller", description = "Ingredients-based recipes recommendations")
public class RecommendationController {

    @Autowired
    private IRecommendationService recommendationService;

    @PostMapping(value = "/ingredients-analysis/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Ingredients recognition from image")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> recognition(@RequestPart MultipartFile image) throws IOException {

        return ResponseModel.builder()
                .isSuccess(true)
                .data(recommendationService.recognition(image))
                .errors(null)
                .build();
    }

    @PostMapping(value = "/suggestion-recipes")
    @Operation(summary = "Get suggestion recipes from ingredients")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseModel<Object> getSuggestions(@RequestBody SuggestRequest ingredientList) {

        return ResponseModel.builder()
                .isSuccess(true)
                .data(recommendationService.getSuggestions(ingredientList.getIngredients()))
                .errors(null)
                .build();
    }


}
