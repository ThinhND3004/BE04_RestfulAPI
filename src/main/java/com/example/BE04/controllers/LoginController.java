package com.example.BE04.controllers;

import com.example.BE04.entity.UserEntity;
import com.example.BE04.payload.response.BaseResponse;
import com.example.BE04.payload.response.TokenResponse;
import com.example.BE04.service.Imp.CreateAccountServiceImp;
import com.example.BE04.service.Imp.LoginServiceImp;
import com.example.BE04.test.LoginTest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginServiceImp loginServiceImp;


    @PostMapping("/login-with-username-and-password")
    public ResponseEntity<?> login(@RequestBody LoginTest user)
    {

        BaseResponse baseResponse = new BaseResponse();

        String token = loginServiceImp.checkLogin(user.getUsername(), user.getPassword());

        baseResponse.setAccessToken(token);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }


    @GetMapping("/profile")
    public ResponseEntity<?> profile(HttpServletRequest request)
    {
        TokenResponse tokenResponse = loginServiceImp.getUsernameFromToken(request);



        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }


}
