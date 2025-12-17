package com.example.finalrpo.service;

import com.example.finalrpo.dto.TaskDTO;
import java.util.List;

public interface TaskService {
    List<TaskDTO> getAll();
    TaskDTO getById(Long id);
    void create(TaskDTO taskDTO);
    void updateTask(Long id, TaskDTO taskDTO);
    void delete(Long id);
}

