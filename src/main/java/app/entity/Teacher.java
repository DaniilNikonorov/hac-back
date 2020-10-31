package app.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(schema = "public", name = "teacher")
public class Teacher {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @OneToMany(mappedBy = "teacher")
    private List<Task> taskList;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
