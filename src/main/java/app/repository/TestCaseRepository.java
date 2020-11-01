package app.repository;

import app.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestCaseRepository extends JpaRepository<TestCase, String> {
    List<TestCase> findAllByTaskId(String taskId);
}
