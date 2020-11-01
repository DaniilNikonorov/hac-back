package app.dto.task;

import app.dto.classSchoolboy.ClassSchoolboyDto;
import app.dto.testTask.TestCasesDto;
import lombok.Data;

import java.util.List;

@Data
public class TaskTeacherDto {

    private String id;

    private String name;

    private String description;

    private String legend;

    private List<TestCasesDto> testTaskList;

    private String teacherId;

    private List<ClassSchoolboyDto> classSchoolboyList;
}
