package com.example.finalrpo.service;

import com.example.finalrpo.dto.ProjectDTO;
import java.util.List;

public interface ProjectService {
    void create(ProjectDTO projectDTO);
    List<ProjectDTO> getAll();
    ProjectDTO getById(Long id);
    void updateProject(Long id, ProjectDTO projectDTO);
    void delete(Long id);
}
