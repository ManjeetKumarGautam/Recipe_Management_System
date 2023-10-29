package com.manjeet.Recipe_Management_System_.API.repository;

import com.manjeet.Recipe_Management_System_.API.model.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenRepo extends JpaRepository<AuthToken,Integer> {

    AuthToken findFirstByTokenValue(String tokenValue);
}
