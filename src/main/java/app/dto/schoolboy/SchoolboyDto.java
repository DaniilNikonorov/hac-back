package app.dto.schoolboy;

import app.dto.task.TaskSchoolboyDto;
import lombok.Data;

import java.util.List;


@Data
public class SchoolboyDto {
    private String id;

    private List<TaskSchoolboyDto> taskList;

}
