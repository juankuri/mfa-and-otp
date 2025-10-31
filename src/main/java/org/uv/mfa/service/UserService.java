package org.uv.mfa.service;

import org.uv.mfa.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    User save(User user);
    boolean checkPassword(User user, String password);
    void delete(Long id);
}
