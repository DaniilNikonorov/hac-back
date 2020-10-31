package app.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(schema = "public", name = "task")
public class Task {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "legend", nullable = false)
    private String legend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;


    @OneToMany(mappedBy = "taskId")
    private List<TestCase> testCaseList;

    @ManyToMany
    @JoinTable(name = "task_class_cross",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    private List<ClassSchoolboy> classSchoolboyList;
}
