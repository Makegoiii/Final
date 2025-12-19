package com.example.finalrpo.service.impl;

import com.example.finalrpo.dto.TaskDTO;
import com.example.finalrpo.mapper.TaskMapper;
import com.example.finalrpo.model.Task;
import com.example.finalrpo.repository.TaskRepository;
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
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void getAll_returnsDtos() {
        Task t = new Task();
        t.setId(1L);
        t.setName("T1");
        t.setStatus("NEW");

        List<Task> entities = List.of(t);
        List<TaskDTO> dtos = List.of(new TaskDTO(1L, "T1", "NEW", null, null));

        when(taskRepository.findAll()).thenReturn(entities);
        when(taskMapper.toDtoList(entities)).thenReturn(dtos);

        List<TaskDTO> result = taskService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("T1", result.get(0).getName());

        verify(taskRepository, times(1)).findAll();
        verify(taskMapper, times(1)).toDtoList(entities);
    }

    @Test
    void getById_returnsDto() {
        Long id = 1L;
        Task entity = new Task();
        entity.setId(id);
        entity.setName("Task1");
        entity.setStatus("NEW");

        TaskDTO dto = new TaskDTO(id, "Task1", "NEW", null, null);

        when(taskRepository.findById(id)).thenReturn(Optional.of(entity));
        when(taskMapper.toDto(entity)).thenReturn(dto);

        TaskDTO result = taskService.getById(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Task1", result.getName());

        verify(taskRepository, times(1)).findById(id);
        verify(taskMapper, times(1)).toDto(entity);
    }

    @Test
    void create_savesMappedEntity() {
        TaskDTO dto = new TaskDTO(null, "NewTask", "NEW", null, null);
        Task entity = new Task();
        entity.setName("NewTask");
        entity.setStatus("NEW");

        when(taskMapper.toEntity(dto)).thenReturn(entity);

        taskService.create(dto);

        verify(taskMapper, times(1)).toEntity(dto);
        verify(taskRepository, times(1)).save(entity);
    }

    @Test
    void updateTask_updatesAndSaves() {
        Long id = 1L;
        Task existing = new Task();
        existing.setId(id);
        existing.setName("OldName");
        existing.setStatus("OLD");

        TaskDTO dto = new TaskDTO(null, "NewName", "NEW", null, null);

        when(taskRepository.findById(id)).thenReturn(Optional.of(existing));

        taskService.updateTask(id, dto);

        assertEquals("NewName", existing.getName());
        assertEquals("NEW", existing.getStatus());

        verify(taskRepository, times(1)).save(existing);
    }

    @Test
    void delete_callsRepositoryDelete() {
        Long id = 1L;

        taskService.delete(id);
        verify(taskRepository, times(1)).deleteById(id);
    }
}
