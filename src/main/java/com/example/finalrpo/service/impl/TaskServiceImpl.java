package com.example.finalrpo.service.impl;

import com.example.finalrpo.dto.TaskDTO;
import com.example.finalrpo.mapper.TaskMapper;
import com.example.finalrpo.model.Task;
import com.example.finalrpo.repository.TaskRepository;
import com.example.finalrpo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public void create(TaskDTO taskDTO) {
        taskRepository.save(taskMapper.toEntity(taskDTO));
    }

    @Override
    public List<TaskDTO> getAll() {
        return taskMapper.toDtoList(taskRepository.findAll());
    }

    @Override
    public TaskDTO getById(Long id) {
        return taskMapper.toDto(taskRepository.findById(id).orElse(null));
    }

    @Override
    public void updateTask(Long id, TaskDTO taskDTO) {
        Task updateTask = taskRepository.findById(id).orElse(null);

        if(taskDTO != null){
            updateTask.setName(taskDTO.getName());
            updateTask.setStatus(taskDTO.getStatus());

        }

        taskRepository.save(updateTask);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
