package com.hcmut.recibook.repository.Recipe;

import com.hcmut.recibook.model.entity.Recipe.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {

    @Query(value = "SELECT * FROM recipe AS rec " +
            "WHERE CONCAT(rec.recipe_name, ' ', rec.description, ' ', rec.image) " +
            "ILIKE %:keyword%", nativeQuery = true)
    Page<Recipe> findPageRecipes(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "select * from recipe where category_id = :categoryId",
    countQuery = "select count(*) from recipe where category_id = :categoryId",
    nativeQuery = true)
    Page<Recipe> findByRecipeCategoryId(@Param("categoryId") UUID categoryId, Pageable pageable);

    @Query(value = "SELECT recipe_id, COUNT(ingredient_id) AS matching_ingredients\n" +
            "FROM recipe_ingredient\n" +
            "WHERE ingredient_id IN (:ingredientList)\n" +
            "GROUP BY recipe_id\n" +
            "HAVING COUNT(ingredient_id) > 0", nativeQuery = true)
    List<SuggestionQuery> getSuggestions(@Param("ingredientList") List<UUID> ingredientList);

    interface SuggestionQuery {
        UUID getRecipe_id();
        int getMatching_ingredients();
    }
}
