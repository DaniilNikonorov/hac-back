package app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "public", name = "tries")
public class Try {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "try_number", nullable = false)
    private String tryNumber;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "input")
    private String input;

    @Column(name = "output")
    private String output;

    @Column(name = "result", nullable = false)
    private Integer result;

    @ManyToOne
    @JoinColumn(name = "schoolboy_id", nullable = false)
    private Schoolboy schoolboy;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

}
