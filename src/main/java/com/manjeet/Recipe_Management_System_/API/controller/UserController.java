package com.manjeet.Recipe_Management_System_.API.controller;

import com.manjeet.Recipe_Management_System_.API.model.Comment;
import com.manjeet.Recipe_Management_System_.API.model.Recipe;
import com.manjeet.Recipe_Management_System_.API.model.User;
import com.manjeet.Recipe_Management_System_.API.model.dto.UpdateRecipe;
import com.manjeet.Recipe_Management_System_.API.service.RecipeService;
import com.manjeet.Recipe_Management_System_.API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RecipeService recipeService;

    @PostMapping("/signup")
    public String signup(@RequestBody User user){
        return userService.signup(user);
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String email, @RequestParam String password){
        return userService.signIn(email, password);
    }

    @PostMapping("/signout")
    public String signout(@RequestParam String email, @RequestParam String tokenValue){
        return userService.signOut(email,tokenValue);
    }


    // recipe api

    @PostMapping("/recipe")
    public String postRecipe(@RequestParam String email, @RequestParam String tokenValue, @RequestBody Recipe recipe){
        return userService.postRecipe(email,tokenValue,recipe);
    }

    @GetMapping("/recipes")
    public List<Recipe> getRecipes(@RequestParam String email, @RequestParam String tokenValue){
        return recipeService.getRecipes(email,tokenValue);
    }
    @GetMapping("/recipes/my")
    public List<Recipe> getMyRecipes(@RequestParam String email, @RequestParam String tokenValue){
        return userService.getMyRecipes(email,tokenValue);
    }

    @DeleteMapping("/recipe/{recipeId}")
    public String deleteRecipe(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer recipeId)
    {
        return userService.deleteRecipe(email,tokenValue,recipeId);
    }


    @PostMapping("/comment/recipe/{recipeId}")
    public String addComment(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer recipeId,@RequestBody String commentBody )
    {
        return userService.addComment(email,tokenValue,commentBody,recipeId);
    }

    @GetMapping("/comment/recipe/{recipeId}")
    public List<Comment> getComment(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer recipeId )
    {
        return userService.getCommentByRecipeId(email,tokenValue,recipeId);
    }

    @DeleteMapping("/recipe/comment/{commentId}")
    public String removeComment(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer commentId)
    {
        return userService.removeComment(email,tokenValue,commentId);
    }


    @PutMapping("/recipe/{recipeId}")
    public String updateRecipe(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer recipeId, @RequestBody UpdateRecipe updationInfo)
    {
        return userService.updateRecipe(email,tokenValue,recipeId,updationInfo);
    }

}
