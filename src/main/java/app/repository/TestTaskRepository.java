package app.repository;

import app.entity.TestTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestTaskRepository extends JpaRepository<TestTask, String> {
}
