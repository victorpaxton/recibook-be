package com.hcmut.recibook.model.entity.Recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcmut.recibook.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "recipe")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipe extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "recipe_name")
    private String recipeName;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String direction;

    @Column(name = "cooking_time")
    private int cookingTime;

    private String image;

    private String cuisine;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private RecipeCategory recipeCategory;

    @OneToMany(mappedBy = "recipe")
    private List<RecipeIngredient> recipeIngredients;
}
