package com.manjeet.Recipe_Management_System_.API.service;

import com.manjeet.Recipe_Management_System_.API.model.User;
import com.manjeet.Recipe_Management_System_.API.model.dto.SignInDto;
import com.manjeet.Recipe_Management_System_.API.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

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

    public String signIn(SignInDto signInInfo) {

        String email = signInInfo.getEmail();

        User existingUser = userRepo.findFirstByEmail(email);

        if(existingUser == null)
        {
            return "Not a valid email, Please sign up first !!!";
        }

        //password should be matched

        String password = signInInfo.getPassword();

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
}
