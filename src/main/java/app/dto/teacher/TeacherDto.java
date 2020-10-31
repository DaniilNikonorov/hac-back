package app.dto.teacher;

import app.dto.task.TaskTeacherDto;
import lombok.Data;

import java.util.List;

@Data
public class TeacherDto {

    private String id;

    private List<TaskTeacherDto> taskList;
}
