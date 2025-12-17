package com.example.finalrpo.mapper;
import com.example.finalrpo.dto.UserDTO;
import com.example.finalrpo.model.User;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);

    List<UserDTO> toDtoList(List<User> users);
}
