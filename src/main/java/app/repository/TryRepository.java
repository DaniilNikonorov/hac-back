package app.repository;

import app.entity.Try;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TryRepository extends JpaRepository<Try, String> {

    List<Try> findAllBySchoolboyIdAndTaskId(String schoolboyId, String taskId);

}
