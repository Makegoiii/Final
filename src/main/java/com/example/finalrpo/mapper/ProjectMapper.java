package com.example.finalrpo.mapper;
import com.example.finalrpo.dto.ProjectDTO;
import com.example.finalrpo.model.Project;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDTO toDto(Project project);
    Project toEntity(ProjectDTO projectDTO);

    List<ProjectDTO> toDtoList(List<Project> projects);
}
