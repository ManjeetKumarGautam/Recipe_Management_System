package com.manjeet.Recipe_Management_System_.API.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer recipeId;
    private String title;
    private List<String> ingredients;
    private List<String> instructions;

    private LocalDateTime postRecipeTimeStamp;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User postOwner;
}
