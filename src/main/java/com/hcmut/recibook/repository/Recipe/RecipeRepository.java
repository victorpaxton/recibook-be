package com.hcmut.recibook.repository.Recipe;

import com.hcmut.recibook.model.entity.Recipe.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {

    @Query(value = "SELECT * FROM recipe AS rec " +
            "WHERE CONCAT(rec.recipe_name, ' ', rec.description, ' ', rec.image) " +
            "LIKE %:keyword%", nativeQuery = true)
    Page<Recipe> findPageRecipes(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "select * from recipe where category_id = :categoryId",
    countQuery = "select count(*) from recipe where category_id = :categoryId",
    nativeQuery = true)
    Page<Recipe> findByRecipeCategoryId(@Param("categoryId") UUID categoryId, Pageable pageable);
}
