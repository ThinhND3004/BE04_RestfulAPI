package com.example.BE04.respository;

import com.example.BE04.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository  extends JpaRepository<UserEntity, String> {
    UserEntity getByUsername(String username);

    UserEntity getById(int id);

}
