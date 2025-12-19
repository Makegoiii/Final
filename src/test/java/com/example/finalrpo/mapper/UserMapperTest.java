package com.example.finalrpo.mapper;
import com.example.finalrpo.dto.UserDTO;
import com.example.finalrpo.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void convertEntityToDtoTest() {
        User entity = new User();
        entity.setId(1L);
        entity.setName("Ivan");
        entity.setSurname("Ivanov");

        UserDTO dto = userMapper.toDto(entity);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getName());
        Assertions.assertNotNull(dto.getSurname());

        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getName(), dto.getName());
        Assertions.assertEquals(entity.getSurname(), dto.getSurname());
    }

    @Test
    void convertDtoToEntityTest() {
        UserDTO dto = new UserDTO(1L, "Petr", "Petrov");

        User entity = userMapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertNotNull(entity.getId());
        Assertions.assertNotNull(entity.getName());
        Assertions.assertNotNull(entity.getSurname());

        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getName(), entity.getName());
        Assertions.assertEquals(dto.getSurname(), entity.getSurname());
    }

    @Test
    void convertListEntityToDtoList() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "A", "A", null));
        users.add(new User(2L, "B", "B", null));
        users.add(new User(3L, "C", "C", null));

        List<UserDTO> dtos = userMapper.toDtoList(users);

        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(0, dtos.size());
        Assertions.assertEquals(users.size(), dtos.size());

        for (int i = 0; i < dtos.size(); i++) {
            UserDTO dto = dtos.get(i);
            User user = users.get(i);

            Assertions.assertNotNull(dto);
            Assertions.assertEquals(user.getId(), dto.getId());
            Assertions.assertEquals(user.getName(), dto.getName());
            Assertions.assertEquals(user.getSurname(), dto.getSurname());
        }
    }
}
