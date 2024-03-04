package com.example.BE04.service.Imp;

import com.example.BE04.payload.response.TokenResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface LoginServiceImp {

    String checkLogin(String username, String password);
    TokenResponse getUsernameFromToken(HttpServletRequest request);
}
