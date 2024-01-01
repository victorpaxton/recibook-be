package com.hcmut.recibook.model.entity.Recipe;

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
@Table(name = "recipe_category")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "recipe_category_name")
    private String recipeCategoryName;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String svg;

    @Column(name = "svg_active", columnDefinition = "text")
    private String svgActive;

    @OneToMany(mappedBy = "recipeCategory")
    @JsonIgnore
    private List<Recipe> recipes;
}
