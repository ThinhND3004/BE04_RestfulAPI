package com.example.BE04.filter;


import com.example.BE04.utils.JwtsUtils;
import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component //dua len ioc
public class CustomeJwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtsUtils jwtsUtils;

    private Gson gson = new Gson();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String headerAuthor = request.getHeader("Authorization");

        if(headerAuthor != null && headerAuthor.trim().length() > 0)
        {
            String token = headerAuthor.substring(7);
            String data = jwtsUtils.descriptToken(token);

            if(data != null)
            {
//                RoleResponse roleResponse = gson.fromJson(data, RoleResponse.class);
//
//                System.out.println("Kiem tra : " + data + " - " + roleResponse.getName());


                List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(data);
                authorityList.add(simpleGrantedAuthority);


                UsernamePasswordAuthenticationToken authen = new UsernamePasswordAuthenticationToken("", "", authorityList);

                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authen);
            }
        }

        filterChain.doFilter(request, response);
    }
}
