package com.example.finalrpo.service.impl;

import com.example.finalrpo.dto.ProjectDTO;
import com.example.finalrpo.mapper.ProjectMapper;
import com.example.finalrpo.model.Project;
import com.example.finalrpo.repository.ProjectRepository;
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
public class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    void create_savesMappedEntity() {
        ProjectDTO dto = new ProjectDTO(null, "RPO", "JAVA");
        Project entity = new Project(null, "RPO", "JAVA", null);

        when(projectMapper.toEntity(dto)).thenReturn(entity);

        projectService.create(dto);

        verify(projectMapper, times(1)).toEntity(dto);
        verify(projectRepository, times(1)).save(entity);
    }

    @Test
    void getAll_returnsDtos() {
        Project p = new Project(1L, "P1", "TYPE1", null);
        List<Project> entities = List.of(p);
        List<ProjectDTO> dtos = List.of(new ProjectDTO(1L, "P1", "TYPE1"));

        when(projectRepository.findAll()).thenReturn(entities);
        when(projectMapper.toDtoList(entities)).thenReturn(dtos);

        List<ProjectDTO> result = projectService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("P1", result.get(0).getName());

        verify(projectRepository, times(1)).findAll();
        verify(projectMapper, times(1)).toDtoList(entities);
    }

    @Test
    void getById_returnsDto() {
        Long id = 1L;
        Project entity = new Project(id, "P1", "TYPE1", null);
        ProjectDTO dto = new ProjectDTO(id, "P1", "TYPE1");

        when(projectRepository.findById(id)).thenReturn(Optional.of(entity));
        when(projectMapper.toDto(entity)).thenReturn(dto);

        ProjectDTO result = projectService.getById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("P1", result.getName());

        verify(projectRepository, times(1)).findById(id);
        verify(projectMapper, times(1)).toDto(entity);
    }

    @Test
    void updateProject_updatesAndSaves() {
        Long id = 1L;
        Project existing = new Project(id, "Old", "OLD_TYPE", null);
        ProjectDTO dto = new ProjectDTO(null, "New", "NEW_TYPE");

        when(projectRepository.findById(id)).thenReturn(Optional.of(existing));

        projectService.updateProject(id, dto);

        assertEquals("New", existing.getName());
        assertEquals("NEW_TYPE", existing.getType());

        verify(projectRepository, times(1)).save(existing);
    }

    @Test
    void delete_callsRepositoryDelete() {
        Long id = 1L;

        projectService.delete(id);

        verify(projectRepository, times(1)).deleteById(id);
    }
}

