package app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "public", name = "test_task")
public class TestCase {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "task_id", nullable = false)
    private String taskId;

    @Column(name = "output", nullable = false)
    private String output;

    @Column(name = "input", nullable = false)
    private String input;

}
