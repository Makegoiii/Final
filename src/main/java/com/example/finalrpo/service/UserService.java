package com.example.finalrpo.service;

import com.example.finalrpo.dto.UserDTO;
import java.util.List;

public interface UserService {
    List<UserDTO> getAll();
    UserDTO getById(Long id);
    void createUser(UserDTO userDTO);
    void updateUser(Long id, UserDTO userDTO);
    void delete(Long id);
}

