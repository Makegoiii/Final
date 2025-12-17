package com.example.finalrpo.controller;

import com.example.finalrpo.dto.ProjectDTO;
import com.example.finalrpo.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;
    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody ProjectDTO projectDTO){
        projectService.create(projectDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public List<ProjectDTO> getAllProjects(){
        return projectService.getAll();
    }

    @GetMapping("/{id}")
    public ProjectDTO getById(@PathVariable Long id){
        return projectService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody ProjectDTO projectDTO ){
        projectService.updateProject(id,projectDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        projectService.delete(id);
    }

}
