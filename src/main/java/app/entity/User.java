package app.entity;

import app.enums.UserRole;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "public", name = "user")
public class User {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "edu")
    private String edu;

    @Column(name = "birthday")
    private String birthday;

}
