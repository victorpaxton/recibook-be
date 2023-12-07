package com.hcmut.recibook.model.entity.Ingredient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcmut.recibook.model.entity.BaseEntity;
import com.hcmut.recibook.model.entity.Recipe.RecipeIngredient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "ingredient")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ingredient extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ingredient_name")
    private String ingredientName;

    @Column(columnDefinition = "text")
    private String description;

    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonIgnore
    private IngredientCategory ingredientCategory;

    @OneToMany(mappedBy = "ingredient")
    @JsonIgnore
    private List<RecipeIngredient> recipeIngredients;
}
