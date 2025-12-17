package com.example.finalrpo.service.impl;
import com.example.finalrpo.dto.ProjectDTO;
import com.example.finalrpo.mapper.ProjectMapper;
import com.example.finalrpo.model.Project;
import com.example.finalrpo.repository.ProjectRepository;
import com.example.finalrpo.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;

    @Override
    public void create(ProjectDTO projectDTO) {
        projectRepository.save(projectMapper.toEntity(projectDTO));

    }

    @Override
    public List<ProjectDTO> getAll() {
        return projectMapper.toDtoList(projectRepository.findAll());
    }

    @Override
    public ProjectDTO getById(Long id) {
        return projectMapper.toDto(projectRepository.findById(id).orElse(null));
    }


    @Override
    public void updateProject(Long id, ProjectDTO projectDTO) {
        Project updateProjectEntity = projectRepository.findById(id).orElse(null);
        if (projectDTO != null) {
            updateProjectEntity.setName(projectDTO.getName());
            updateProjectEntity.setType(projectDTO.getType());
        }
        projectRepository.save(updateProjectEntity);
    }



    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}


