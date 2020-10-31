package app.service;

import app.entity.User;
import app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

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
        }
    }

}
