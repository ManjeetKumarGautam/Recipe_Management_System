package com.manjeet.Recipe_Management_System_.API.service;


import com.manjeet.Recipe_Management_System_.API.model.AuthToken;
import com.manjeet.Recipe_Management_System_.API.repository.ITokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    ITokenRepo tokenRepo;
    public void createToken(AuthToken token) {
        tokenRepo.save(token);
    }

    public void deleteToken(String tokenValue) {

        AuthToken token =  tokenRepo.findFirstByTokenValue(tokenValue);
        tokenRepo.delete(token);

    }

    public boolean authenticate(String email,String tokenValue) {

        AuthToken token =  tokenRepo.findFirstByTokenValue(tokenValue);
        if(token!=null)
        {
            return token.getUser().getEmail().equals(email);
        }
        else
        {
            return false;
        }

    }
}
