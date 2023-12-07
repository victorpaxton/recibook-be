package com.hcmut.recibook.model.entity.Recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcmut.recibook.model.entity.BaseEntity;
import com.hcmut.recibook.model.entity.Ingredient.Ingredient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "recipe_ingredient")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeIngredient extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    @JsonIgnore
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    private Ingredient ingredient;

    private int amount;
}
