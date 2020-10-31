package app.service;

import app.entity.ClassSchoolboy;
import app.entity.Schoolboy;
import app.entity.Teacher;
import app.entity.User;
import app.enums.UserRole;
import app.repository.SchoolboyRepository;
import app.repository.TeacherRepository;
import app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final TeacherRepository teacherRepository;
    private final SchoolboyRepository schoolboyRepository;

    public Optional<User> authorize(String login, String password) {
        return repository.findByLoginAndPassword(login, password);
    }

    public Optional<User> getById(String id) {
        return repository.findById(id);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public void delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }    }

    public User save(User user) {
         User savedUser = repository.save(user);
         if (savedUser.getRole().equals(UserRole.TEACHER)) {
            Teacher teacher = new Teacher();
            teacher.setId(java.util.UUID.randomUUID().toString());
            teacher.setUser(savedUser);
            teacherRepository.save(teacher);
         } else if (savedUser.getRole().equals(UserRole.SCHOOLBOY)) {
             Schoolboy schoolboy = new Schoolboy();
             schoolboy.setId(java.util.UUID.randomUUID().toString());
             schoolboy.setUser(savedUser);
             ClassSchoolboy classSchoolboy = new ClassSchoolboy();
             classSchoolboy.setId("1");
             schoolboy.setClassSchoolboy(classSchoolboy);
             schoolboyRepository.save(schoolboy);
         }
         return savedUser;
    }

}
