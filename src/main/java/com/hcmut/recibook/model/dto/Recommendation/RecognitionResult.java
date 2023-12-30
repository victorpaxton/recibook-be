package com.hcmut.recibook.model.dto.Recommendation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecognitionResult {

    private Image image;
    private List<Prediction> predictions;

    @Data
    public static class Image {
        private int width;
        private int height;
    }

    @Data
    public static class Prediction {
        private double x;
        private double y;
        private double width;
        private double height;
        private double confidence;
        private String CLASS;
    }
}
