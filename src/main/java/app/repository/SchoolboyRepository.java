package app.repository;

import app.entity.Schoolboy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolboyRepository extends JpaRepository<Schoolboy, String> {

    Optional<Schoolboy> findByUserId(String id);
}
