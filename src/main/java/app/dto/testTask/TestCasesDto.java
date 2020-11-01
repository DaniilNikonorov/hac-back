package app.dto.testTask;

import app.entity.TestCase;
import lombok.Data;

@Data
public class TestCasesDto {

    private String id;

    private String input;

    private String output;

    public static TestCasesDto toDto(TestCase entity) {
        TestCasesDto dto = new TestCasesDto();
        dto.setId(entity.getId());
        dto.setOutput(entity.getOutput());
        dto.setInput(entity.getInput());

        return dto;
    }

}
