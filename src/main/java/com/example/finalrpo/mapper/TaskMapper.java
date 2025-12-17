package com.example.finalrpo.mapper;
import com.example.finalrpo.dto.TaskDTO;
import com.example.finalrpo.model.Task;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring", uses ={UserMapper.class, ProjectMapper.class})
public interface TaskMapper {
    TaskDTO toDto(Task task);
    Task toEntity(TaskDTO taskDTO);

    List<TaskDTO> toDtoList(List<Task> tasks);
}
