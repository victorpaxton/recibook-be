package com.hcmut.recibook.service;

import com.hcmut.recibook.model.dto.Recommendation.RecognitionResponse;
import com.hcmut.recibook.model.dto.Recommendation.SuggestRecipe;
import com.hcmut.recibook.model.entity.Ingredient.Ingredient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IRecommendationService {
    public RecognitionResponse recognition(MultipartFile file) throws IOException;

    public List<SuggestRecipe> getSuggestions(List<UUID> ingredientList);
}
