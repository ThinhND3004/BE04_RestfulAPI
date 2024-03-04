package com.example.BE04.controllers;

import com.example.BE04.payload.response.BaseResponse;
import com.example.BE04.payload.response.TokenResponse;
import com.example.BE04.service.Imp.LoginServiceImp;
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
@CrossOrigin(origins = "http://localhost:5173")
public class LoginController {

    @Autowired
    private LoginServiceImp loginServiceImp;
    @PostMapping("/login-with-username-and-password")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password)
    {
//        SecretKey secretKey = Jwts.SIG.HS256.key().build();
//        String secretString = Encoders.BASE64.encode(secretKey.getEncoded());
//        System.out.println("Kiểm tra: " + secretString);

        BaseResponse baseResponse = new BaseResponse();

        String token = loginServiceImp.checkLogin(username, password);

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
