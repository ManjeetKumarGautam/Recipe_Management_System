package com.manjeet.Recipe_Management_System_.API.repository;

import com.manjeet.Recipe_Management_System_.API.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecipeRepo extends JpaRepository<Recipe,Integer> {
}
