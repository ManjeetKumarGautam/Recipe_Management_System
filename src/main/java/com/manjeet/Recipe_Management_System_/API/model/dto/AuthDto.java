package com.manjeet.Recipe_Management_System_.API.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {
    private String email;
    private String tokenValue;
}