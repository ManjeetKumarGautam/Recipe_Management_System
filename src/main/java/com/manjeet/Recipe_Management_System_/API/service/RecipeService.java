package com.manjeet.Recipe_Management_System_.API.service;

import com.manjeet.Recipe_Management_System_.API.model.Recipe;
import com.manjeet.Recipe_Management_System_.API.model.User;
import com.manjeet.Recipe_Management_System_.API.model.dto.UpdateRecipe;
import com.manjeet.Recipe_Management_System_.API.repository.IRecipeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    IRecipeRepo recipeRepo;

    @Autowired
    TokenService tokenService;

    @Autowired
    CommentService commentService;
    public void postRecipe(Recipe recipe) {

        //set creation time before saving :
        recipe.setPostRecipeTimeStamp(LocalDateTime.now());
        recipeRepo.save(recipe);
    }

    public Recipe getRecipeById(Integer recipeId) {
        return  recipeRepo.findById(recipeId).orElseThrow();

    }

    public List<Recipe> getRecipes(String email, String tokenValue) {
        if(tokenService.authenticate(email, tokenValue)){
            return recipeRepo.findAll();
        }
        else {
            return null;
        }
    }

    public List<Recipe> getMyRecipes(User user) {
        return recipeRepo.findByPostOwner(user);
    }

    @Transactional
    public String deleteRecipeById(Integer id){
        Recipe recipe= recipeRepo.findById(id).orElseThrow();
        commentService.clearCommentsByRecipe(recipe);
        recipeRepo.deleteById(id);
        return "Deleted..";
    }

    @Transactional
    public String updateRecipe(Integer recipeId, UpdateRecipe updationInfo) {
        if(updationInfo.getTitle()!=null){
            recipeRepo.updateTitle(updationInfo.getTitle(),recipeId);
        }
        if(updationInfo.getIngredients()!=null){
            recipeRepo.updateIngredients(updationInfo.getIngredients(),recipeId);
        }
        if (updationInfo.getInstructions()!=null){
            recipeRepo.updateInstructions(updationInfo.getInstructions(),recipeId);
        }
        return "Updated...";
    }
}
