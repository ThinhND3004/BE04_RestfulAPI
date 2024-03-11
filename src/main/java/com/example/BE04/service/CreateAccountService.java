package com.example.BE04.service;

import com.example.BE04.entity.UserEntity;
import com.example.BE04.respository.UserRespository;
import com.example.BE04.service.Imp.CreateAccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountService implements CreateAccountServiceImp {

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserEntity creatAccount(String username, String password) {

        String enCodedPassword = passwordEncoder.encode(password);


        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(enCodedPassword);

        UserEntity result = userRespository.save(userEntity);

        return result;
    }
}
