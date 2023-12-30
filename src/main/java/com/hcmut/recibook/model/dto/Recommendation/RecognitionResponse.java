package com.hcmut.recibook.model.dto.Recommendation;

import com.hcmut.recibook.model.entity.Ingredient.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecognitionResponse {
    private Set<Ingredient> ingredientList;
    private Object recognitionResult;
}
