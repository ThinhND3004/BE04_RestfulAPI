package com.example.BE04.service;

import com.example.BE04.entity.UserEntity;
import com.example.BE04.payload.response.TokenResponse;
import com.example.BE04.respository.UserRespository;
import com.example.BE04.service.Imp.LoginServiceImp;
import com.example.BE04.utils.JwtsUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService implements LoginServiceImp {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private JwtsUtils jwtsUtils;
    private Gson gson = new Gson();

    @Override
    public String checkLogin(String username, String password) {

        String token = "";
        UserEntity user = userRespository.getByUsername(username);
        if (passwordEncoder.matches(password, user.getPassword())) {
//            TokenResponse tokenResponse = new TokenResponse();
////
////            tokenResponse.setUsername(user.getUsername());
////            tokenResponse.setPassword(user.getPassword());
////
            String userInfo = gson.toJson(user.getId());
            token = jwtsUtils.createToken(userInfo);

        }
        return token;
    }

    @Override
    public TokenResponse getUsernameFromToken(HttpServletRequest request) {
        String headerAuthor = request.getHeader("Authorization");
        TokenResponse result = new TokenResponse();

        if (headerAuthor != null && headerAuthor.trim().length() > 0) {
            String token = headerAuthor.substring(7);
            String data = jwtsUtils.descriptToken(token);

            if (data != null) {
                UserEntity user = userRespository.getById(Integer.parseInt(data));
                if (user != null) {
                    result.setId(user.getId());
                    result.setUsername(user.getUsername());
                }
            }
        }
            return result;
        }
    }


