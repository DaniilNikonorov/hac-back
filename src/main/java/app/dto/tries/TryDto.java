package app.dto.tries;

import lombok.Data;

@Data
public class TryDto {
    private String id;

    private String taskId;

    private String schoolboyId;

    private String tryNumber;

    private String code;

    private String input;

    private String output;

    private Integer result;
}
