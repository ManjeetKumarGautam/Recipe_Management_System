package com.manjeet.Recipe_Management_System_.API.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRecipe {
    private String title;
    private List<String> ingredients;
    private List<String> instructions;
}
