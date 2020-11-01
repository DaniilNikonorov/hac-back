package app.dto.task;

import app.dto.testTask.TestCasesDto;
import lombok.Data;

import java.util.List;

@Data
public class TaskSchoolboyDto {

    private String id;

    private String name;

    private String description;

    private String legend;

    private List<TestCasesDto> testCasesList;

    private Integer bestResult;
}
