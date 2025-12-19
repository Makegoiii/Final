package com.example.finalrpo.repository;

import com.example.finalrpo.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRep extends JpaRepository<Permission, Long> {
    Permission findByName(String name);
}
