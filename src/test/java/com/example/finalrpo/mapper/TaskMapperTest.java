package com.example.finalrpo.mapper;

import com.example.finalrpo.dto.ProjectDTO;
import com.example.finalrpo.dto.TaskDTO;
import com.example.finalrpo.dto.UserDTO;
import com.example.finalrpo.model.Project;
import com.example.finalrpo.model.Task;
import com.example.finalrpo.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void convertEntityToDtoTest() {
        User user = new User(1L, "Ivan", "Ivanov", null);
        List<Project> projects = new ArrayList<>();
        projects.add(new Project(1L, "P1", "TYPE1", null));
        projects.add(new Project(2L, "P2", "TYPE2", null));

        Task task = new Task(1L, "Task1", "NEW", user, projects);

        TaskDTO dto = taskMapper.toDto(task);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getName());
        Assertions.assertNotNull(dto.getStatus());
        Assertions.assertNotNull(dto.getUser());
        Assertions.assertNotNull(dto.getProjects());

        Assertions.assertEquals(task.getId(), dto.getId());
        Assertions.assertEquals(task.getName(), dto.getName());
        Assertions.assertEquals(task.getStatus(), dto.getStatus());

        Assertions.assertEquals(user.getId(), dto.getUser().getId());
        Assertions.assertEquals(user.getName(), dto.getUser().getName());
        Assertions.assertEquals(user.getSurname(), dto.getUser().getSurname());

        Assertions.assertEquals(projects.size(), dto.getProjects().size());
    }

    @Test
    void convertDtoToEntityTest() {
        UserDTO userDTO = new UserDTO(1L, "Petr", "Petrov");
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        projectDTOs.add(new ProjectDTO(1L, "A", "T1"));
        projectDTOs.add(new ProjectDTO(2L, "B", "T2"));

        TaskDTO dto = new TaskDTO(1L, "SomeTask", "IN_PROGRESS", userDTO, projectDTOs);

        Task entity = taskMapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertNotNull(entity.getId());
        Assertions.assertNotNull(entity.getName());
        Assertions.assertNotNull(entity.getStatus());
        Assertions.assertNotNull(entity.getUser());
        Assertions.assertNotNull(entity.getProjects());

        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getName(), entity.getName());
        Assertions.assertEquals(dto.getStatus(), entity.getStatus());

        Assertions.assertEquals(userDTO.getId(), entity.getUser().getId());
        Assertions.assertEquals(userDTO.getName(), entity.getUser().getName());
        Assertions.assertEquals(userDTO.getSurname(), entity.getUser().getSurname());

        Assertions.assertEquals(projectDTOs.size(), entity.getProjects().size());
    }

    @Test
    void convertListEntityToDtoList() {
        User user = new User(1L, "Ivan", "Ivanov", null);

        List<Project> projects = new ArrayList<>();
        projects.add(new Project(1L, "P1", "TYPE1", null));

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "T1", "NEW", user, projects));
        tasks.add(new Task(2L, "T2", "DONE", user, projects));

        List<TaskDTO> dtos = taskMapper.toDtoList(tasks);

        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(0, dtos.size());
        Assertions.assertEquals(tasks.size(), dtos.size());
    }
}