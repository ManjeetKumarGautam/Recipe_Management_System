package com.manjeet.Recipe_Management_System_.API.repository;

import com.manjeet.Recipe_Management_System_.API.model.Comment;
import com.manjeet.Recipe_Management_System_.API.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepo extends JpaRepository<Comment,Integer> {

    List<Comment> findByRecipe(Recipe recipe);
}
