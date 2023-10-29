package com.manjeet.Recipe_Management_System_.API.service;

import com.manjeet.Recipe_Management_System_.API.repository.IRecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    @Autowired
    IRecipeRepo recipeRepo;
}
