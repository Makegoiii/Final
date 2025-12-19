package com.example.finalrpo.service.impl;

import com.example.finalrpo.dto.UserDTO;
import com.example.finalrpo.mapper.UserMapper;
import com.example.finalrpo.model.User;
import com.example.finalrpo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getAll_returnsListOfDtos() {
        User u1 = new User(1L, "Ivan", "Ivanov", null);
        List<User> entities = List.of(u1);
        List<UserDTO> dtos = List.of(new UserDTO(1L, "Ivan", "Ivanov"));

        when(userRepository.findAll()).thenReturn(entities);
        when(userMapper.toDtoList(entities)).thenReturn(dtos);

        List<UserDTO> result = userService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Ivan", result.get(0).getName());

        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(1)).toDtoList(entities);
    }

    @Test
    void getById_returnsDto() {
        Long id = 1L;
        User entity = new User(id, "Petr", "Petrov", null);
        UserDTO dto = new UserDTO(id, "Petr", "Petrov");

        when(userRepository.findById(id)).thenReturn(Optional.of(entity));
        when(userMapper.toDto(entity)).thenReturn(dto);

        UserDTO result = userService.getById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Petr", result.getName());

        verify(userRepository, times(1)).findById(id);
        verify(userMapper, times(1)).toDto(entity);
    }

    @Test
    void createUser_savesMappedEntity() {
        UserDTO dto = new UserDTO(null, "New", "User");
        User entity = new User(null, "New", "User", null);

        when(userMapper.toEntity(dto)).thenReturn(entity);

        userService.createUser(dto);

        verify(userMapper, times(1)).toEntity(dto);
        verify(userRepository, times(1)).save(entity);
    }

    @Test
    void updateUser_updatesAndSaves() {
        Long id = 1L;
        User existing = new User(id, "OldName", "OldSurname", null);
        UserDTO dto = new UserDTO(null, "NewName", "NewSurname");

        when(userRepository.findById(id)).thenReturn(Optional.of(existing));
        userService.updateUser(id, dto);
        assertEquals("NewName", existing.getName());
        assertEquals("NewSurname", existing.getSurname());
        verify(userRepository, times(1)).save(existing);
    }

    @Test
    void delete_callsRepositoryDelete() {
        Long id = 1L;
        userService.delete(id);
        verify(userRepository, times(1)).deleteById(id);
    }
}
