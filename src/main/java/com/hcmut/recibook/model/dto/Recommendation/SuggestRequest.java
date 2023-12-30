package com.hcmut.recibook.model.dto.Recommendation;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SuggestRequest {
    private List<UUID> ingredients;
}
