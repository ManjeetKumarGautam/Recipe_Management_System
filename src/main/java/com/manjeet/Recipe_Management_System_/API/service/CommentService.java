package com.manjeet.Recipe_Management_System_.API.service;

import com.manjeet.Recipe_Management_System_.API.model.Comment;
import com.manjeet.Recipe_Management_System_.API.model.Recipe;
import com.manjeet.Recipe_Management_System_.API.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    ICommentRepo commentRepo;

    public void clearCommentsByRecipe(Recipe recipe) {

        List<Comment> commentsOfRecipePost = commentRepo.findByRecipe(recipe);
        commentRepo.deleteAll(commentsOfRecipePost);
    }

    public void addComment(Comment newComment) {
        commentRepo.save(newComment);
    }

    public Comment findCommentById(Integer commentId) {
        return commentRepo.findById(commentId).orElseThrow();
    }

    public void removeCommentById(Integer commentId) {
        commentRepo.deleteById(commentId);
    }


    public List<Comment> getCommentOnRecipeId(Recipe existingRecipe) {
        return commentRepo.findByRecipe(existingRecipe);
    }
}