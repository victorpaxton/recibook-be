package com.hcmut.recibook.model.entity.Ingredient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcmut.recibook.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "ingredient_category")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ingredient_category_name")
    private String ingredientCategoryName;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String svg;

    @Column(name = "svg_active", columnDefinition = "text")
    private String svgActive;

    @OneToMany(mappedBy = "ingredientCategory")
    @JsonIgnore
    private List<Ingredient> ingredients;
}
