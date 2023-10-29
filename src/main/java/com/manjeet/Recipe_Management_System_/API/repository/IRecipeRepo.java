package com.manjeet.Recipe_Management_System_.API.repository;

import com.manjeet.Recipe_Management_System_.API.model.Recipe;
import com.manjeet.Recipe_Management_System_.API.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRecipeRepo extends JpaRepository<Recipe,Integer> {

    List<Recipe> findByPostOwner(User user);

    @Modifying
    @Query(value = "Update recipe set title = :title where recipe_id=:recipeId",nativeQuery = true)
    void updateTitle(String title, Integer recipeId);

    @Modifying
    @Query(value = "Update recipe set  ingredients= :ingredients where recipe_id=:recipeId",nativeQuery = true)
    void updateIngredients(List<String> ingredients, Integer recipeId);

    @Modifying
    @Query(value = "Update recipe set instructions = :instructions where recipe_id=:recipeId",nativeQuery = true)
    void updateInstructions(List<String> instructions, Integer recipeId);
}
