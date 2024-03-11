package com.example.BE04.controllers;

import com.example.BE04.entity.UserEntity;
import com.example.BE04.payload.response.TokenResponse;
import com.example.BE04.service.Imp.CreateAccountServiceImp;
import com.example.BE04.test.LoginTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
public class CreateAccountController {

    @Autowired
    private CreateAccountServiceImp createAccountServiceImp;
    @PostMapping("/")
    public ResponseEntity<?> createAccount(@RequestBody LoginTest loginTest)
    {
        UserEntity result = createAccountServiceImp.creatAccount(loginTest.getUsername(), loginTest.getPassword());

        TokenResponse newToken = new TokenResponse();

        newToken.setId(result.getId());
        newToken.setUsername(result.getUsername());

        return new ResponseEntity<>(newToken, HttpStatus.OK);
    }

}
