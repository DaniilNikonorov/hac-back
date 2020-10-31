package app.service;

import app.dto.task.TaskSchoolboyDto;
import app.dto.task.TaskTeacherDto;
import app.dto.teacher.TeacherDto;
import app.entity.Task;
import app.entity.Teacher;
import app.entity.Try;
import app.repository.TeacherRepository;
import app.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository repository;

    public Optional<TeacherDto> getByUserId(String id) {
        return repository.findByUserId(id).map(this::toDto);
    }

    private TeacherDto toDto(Teacher entity) {
        TeacherDto dto = new TeacherDto();
        dto.setId(entity.getId());
        List<Task> taskList = entity.getTaskList();
        if (taskList.size() > 0) {
            List<TaskTeacherDto> taskListDto = taskList
                    .stream()
                    .map(TaskService::toDto)
                    .collect(Collectors.toList());
            dto.setTaskList(taskListDto);
        }
        return dto;
    }

}
