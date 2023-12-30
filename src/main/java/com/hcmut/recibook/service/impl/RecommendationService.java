package com.hcmut.recibook.service.impl;

import com.hcmut.recibook.file.CloudinaryService;
import com.hcmut.recibook.model.dto.Recommendation.RecognitionResponse;
import com.hcmut.recibook.model.dto.Recommendation.RecognitionResult;
import com.hcmut.recibook.model.dto.Recommendation.SuggestRecipe;
import com.hcmut.recibook.model.entity.Ingredient.Ingredient;
import com.hcmut.recibook.model.entity.Recipe.Recipe;
import com.hcmut.recibook.repository.Ingredient.IngredientRepository;
import com.hcmut.recibook.repository.Recipe.RecipeRepository;
import com.hcmut.recibook.service.IRecommendationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class RecommendationService implements IRecommendationService {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public RecognitionResponse recognition(MultipartFile file) throws IOException {
        String imageUrl = cloudinaryService.uploadFile("recognition", file);

        RestTemplate restTemplate = new RestTemplate();

        String uri = "https://detect.roboflow.com/food-ingredients-dataset/3?api_key={api_key}&image={image}"; // or any other uri

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<?> result = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class, "9uOjmb4CRDnBSNvpcxVw", imageUrl);

        RecognitionResult recognitionResult = modelMapper.map(result.getBody(), RecognitionResult.class);

        Set<Ingredient> ingredients = new HashSet<>();

        recognitionResult.getPredictions()
                .forEach(p -> ingredients.addAll(ingredientRepository.findByKeyword(p.getCLASS())));

        return RecognitionResponse.builder()
                .ingredientList(ingredients)
                .recognitionResult(recognitionResult)
                .build();
    }

    @Override
    public List<SuggestRecipe> getSuggestions(List<UUID> ingredientList) {
        return recipeRepository.getSuggestions(ingredientList)
                .stream().map(s -> {
                    Recipe recipe = recipeRepository.findById(s.getRecipe_id()).orElseThrow();
                    SuggestRecipe suggestRecipe = modelMapper.map(recipe, SuggestRecipe.class);
                    suggestRecipe.setMissingIngredient(recipe.getRecipeIngredients().size() - s.getMatching_ingredients());
                    return suggestRecipe;
                }).toList();
    }
}
// result.getBody()
//{
//  "data": {
//    "visualization": null,
//    "frame_id": null,
//    "time": 0.21610695399976976,
//    "image": {
//      "width": 640,
//      "height": 480
//    },
//    "predictions": [
//      {
//        "x": 440.5,
//        "y": 389,
//        "width": 317,
//        "height": 182,
//        "confidence": 0.8536379337310791,
//        "class": "Tomato",
//        "class_confidence": null,
//        "class_id": 108,
//        "tracker_id": null
//      },
//      {
//        "x": 314.5,
//        "y": 259.5,
//        "width": 579,
//        "height": 399,
//        "confidence": 0.48349979519844055,
//        "class": "Capsicum",
//        "class_confidence": null,
//        "class_id": 25,
//        "tracker_id": null
//      }
//    ]
//  },
//  "errors": null,
//  "success": true
//}
