package controller;

import entities.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import repository.UserRepository;
import service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (!optionalUser.isEmpty()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }

    @PostMapping
    public ResponseEntity<User> post(@RequestBody User user) {
        User newUser = userService.save(user);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<User> optUser = userService.findById(id);
        if (optUser.isPresent()) {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> put(@RequestBody User user, @PathVariable Long id) {
        Optional<User> optUser = userService.findById(id);

        if (optUser.isPresent()) {
            User updUser = optUser.get();
            updUser.setName(user.getName());
            updUser.setLastname(user.getLastname());
            updUser.setEmail(user.getEmail());
            updUser.setPassword(user.getPassword());

            userService.save(updUser);
            return ResponseEntity.ok(updUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
