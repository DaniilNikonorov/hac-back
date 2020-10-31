package app.repository;

import app.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, String> {

    Optional<Teacher> findByUserId(String id);
}
