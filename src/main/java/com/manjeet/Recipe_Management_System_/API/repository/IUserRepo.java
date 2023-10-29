package com.manjeet.Recipe_Management_System_.API.repository;

import com.manjeet.Recipe_Management_System_.API.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User, Integer> {
    User findFirstByEmail(String newEmail);

}
