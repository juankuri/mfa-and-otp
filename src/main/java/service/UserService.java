package service;

import entities.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findByID(Long id);
    User save(User user);
    void delete(Long id);
}
