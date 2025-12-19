package com.example.finalrpo.repository;

import com.example.finalrpo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRep extends JpaRepository<UserModel, Long> {
    UserModel findByEmail(String email);
}