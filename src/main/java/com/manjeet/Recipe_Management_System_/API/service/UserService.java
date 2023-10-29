package com.manjeet.Recipe_Management_System_.API.service;

import com.manjeet.Recipe_Management_System_.API.model.AuthToken;
import com.manjeet.Recipe_Management_System_.API.model.Comment;
import com.manjeet.Recipe_Management_System_.API.model.Recipe;
import com.manjeet.Recipe_Management_System_.API.model.User;
import com.manjeet.Recipe_Management_System_.API.model.dto.UpdateRecipe;
import com.manjeet.Recipe_Management_System_.API.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    TokenService tokenService;

    @Autowired
    RecipeService recipeService;

    @Autowired
    CommentService commentService;

    public String signup(User user) {

        String newEmail = user.getEmail();

        User existingUser= userRepo.findFirstByEmail(newEmail);

        if(existingUser != null){
            return "Email already in use...";
        }
        String signUpPassword = user.getPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);

            user.setPassword(encryptedPassword);


            // User table - save User
            userRepo.save(user);
            return "User registered";

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }

    }

    public String signIn(String email, String password) {

        User existingUser = userRepo.findFirstByEmail(email);

        if(existingUser == null)
        {
            return "Not a valid email, Please sign up first !!!";
        }

        //password should be matched
        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if(existingUser.getPassword().equals(encryptedPassword))
            {
                // return a token for this sign in
                AuthToken token  = new AuthToken(existingUser);
                tokenService.createToken(token);
                return token.getTokenValue();

            }
            else {
                //password was wrong!!!
                return "Invalid Credentials!!!";
            }

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }

    }

    public String signOut(String email, String tokenValue) {

        if(tokenService.authenticate(email,tokenValue)) {
            tokenService.deleteToken(tokenValue);
            return "Sign Out successful!!";
        }
        else {
            return "Unauthenticated access!!!";
        }

    }

    public String postRecipe(String email, String tokenValue, Recipe recipe) {
        if(tokenService.authenticate(email, tokenValue)) {

            User existingUser = userRepo.findFirstByEmail(email);
            recipe.setPostOwner(existingUser);
            recipeService.postRecipe(recipe);

            return "New Recipe Posted...";

        }
        else {
            return "Un Authenticated access...";
        }
    }

    public List<Recipe> getMyRecipes(String email, String tokenValue) {
        if(tokenService.authenticate(email, tokenValue)){
            User user = userRepo.findFirstByEmail(email);
            return recipeService.getMyRecipes(user);
        }
        else {
            return null;
        }
    }
    public String deleteRecipe(String email, String tokenValue, Integer postId) {

        if(tokenService.authenticate(email,tokenValue)) {

            Recipe recipe =  recipeService.getRecipeById(postId);
            String  postOwnerEmail =  recipe.getPostOwner().getEmail();

            if(email.equals(postOwnerEmail))
            {

                //finally delete the insta post
                recipeService.deleteRecipeById(postId);
                return "Recipe removed!!";

            }
            else {
                return "Un authorized access!!";
            }


        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public String addComment(String email, String tokenValue, String commentBody,Integer recipeId) {

        if(tokenService.authenticate(email,tokenValue)) {

            //figure out the post which we are commenting
            Recipe recipePostToBeCommented = recipeService.getRecipeById(recipeId);

            //we have to figure out the commentor
            User commentor = userRepo.findFirstByEmail(email);

            // functionally more than 1 comment can be made on a particular post

            Comment newComment = new Comment(null,commentBody,
                    LocalDateTime.now(), recipePostToBeCommented, commentor);

            commentService.addComment(newComment);

            return commentor.getName() + " commented on " + recipeId;


        }
        else {
            return "Un Authenticated access!!!";
        }
    }



    public String removeComment(String email, String tokenValue, Integer commentId) {

        if(tokenService.authenticate(email,tokenValue)) {
            Comment comment = commentService.findCommentById(commentId);

            Recipe recipePostOfComment = comment.getRecipe();


            if(authorizeCommentRemover(email,recipePostOfComment,comment))
            {
                commentService.removeCommentById(commentId);
                return "comment deleted";
            }
            else {
                return "Not authorized!!";
            }

        }
        else {
            return "Un Authenticated access!!!";
        }

    }

    private boolean authorizeCommentRemover(String email,Recipe instaPostOfComment, Comment comment) {

        User potentialRemover = userRepo.findFirstByEmail(email);

        return potentialRemover.equals(instaPostOfComment.getPostOwner()) || potentialRemover.equals(comment.getCommenter());

    }

    public String updateRecipe(String email, String tokenValue, Integer recipeId, UpdateRecipe updationInfo) {
        if(tokenService.authenticate(email, tokenValue)){

            String postOwnerEmail= recipeService.getRecipeById(recipeId).getPostOwner().getEmail();

            if(email.equals(postOwnerEmail)){
                return recipeService.updateRecipe(recipeId,updationInfo);
            }
            else {
                return "Not authorized!!";
            }
        }
        else {
            return "Un Authenticated access...";
        }
    }


    public List<Comment> getCommentByRecipeId(String email, String tokenValue, Integer recipeId) {
        if(tokenService.authenticate(email, tokenValue)){

                Recipe existingRecipe= recipeService.getRecipeById(recipeId);

                if(existingRecipe != null){
                    return commentService.getCommentOnRecipeId(existingRecipe);
                }
        }
        return null;
    }
}
