package com.example.finalrpo.mapper;

import com.example.finalrpo.dto.ProjectDTO;
import com.example.finalrpo.model.Project;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProjectMapperTest {

    @Autowired
    private ProjectMapper projectMapper;

    @Test
    void convertEntityToDtoTest() {
        Project entity = new Project();
        entity.setId(1L);
        entity.setName("RPO");
        entity.setType("JAVA");

        ProjectDTO dto = projectMapper.toDto(entity);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getName());
        Assertions.assertNotNull(dto.getType());

        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getName(), dto.getName());
        Assertions.assertEquals(entity.getType(), dto.getType());
    }

    @Test
    void convertDtoToEntityTest() {
        ProjectDTO dto = new ProjectDTO(1L, "CRM", "WEB");

        Project entity = projectMapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertNotNull(entity.getId());
        Assertions.assertNotNull(entity.getName());
        Assertions.assertNotNull(entity.getType());

        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getName(), entity.getName());
        Assertions.assertEquals(dto.getType(), entity.getType());
    }

    @Test
    void convertListEntityToDtoList() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project(1L, "P1", "TYPE1", null));
        projects.add(new Project(2L, "P2", "TYPE2", null));
        projects.add(new Project(3L, "P3", "TYPE3", null));

        List<ProjectDTO> dtos = projectMapper.toDtoList(projects);

        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(0, dtos.size());
        Assertions.assertEquals(projects.size(), dtos.size());

        for (int i = 0; i < dtos.size(); i++) {
            ProjectDTO dto = dtos.get(i);
            Project project = projects.get(i);
            Assertions.assertNotNull(dto);
            Assertions.assertEquals(project.getId(), dto.getId());
            Assertions.assertEquals(project.getName(), dto.getName());
            Assertions.assertEquals(project.getType(), dto.getType());
        }
    }
}
