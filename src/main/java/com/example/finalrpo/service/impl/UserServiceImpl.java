package com.example.finalrpo.service.impl;

import com.example.finalrpo.dto.UserDTO;
import com.example.finalrpo.mapper.UserMapper;
import com.example.finalrpo.model.User;
import com.example.finalrpo.repository.UserRepository;
import com.example.finalrpo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void createUser(UserDTO userDTO) {
        userRepository.save(userMapper.toEntity(userDTO));
    }

    @Override
    public List<UserDTO> getAll() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public UserDTO getById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElse(null));
    }

    @Override
    public void updateUser(Long id, UserDTO userDTO) {
        User updateUser = userRepository.findById(id).orElse(null);

        if(userDTO != null){
            updateUser.setName(userDTO.getName());
            updateUser.setSurname(userDTO.getSurname());

        }

        userRepository.save(updateUser);
    }
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}

