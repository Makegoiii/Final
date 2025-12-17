package com.example.finalrpo.controller;
import com.example.finalrpo.dto.TaskDTO;
import com.example.finalrpo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO){
        taskService.create(taskDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public List<TaskDTO> getAllTasks(){
        return taskService.getAll();
    }
    @GetMapping("/{id}")
    public TaskDTO getById(@PathVariable Long id){
        return taskService.getById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody TaskDTO taskDTO){
        taskService.updateTask(id,taskDTO);
        return new ResponseEntity<>(HttpStatus.OK);    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        taskService.delete(id);
    }
}
