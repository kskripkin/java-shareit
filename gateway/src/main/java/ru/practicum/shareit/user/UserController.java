package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.model.User;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<Object> getUser(@PathVariable long id) {
        log.info("GET /users/{}", id);
        return userService.getUser(id);
    }

    @GetMapping
    public ResponseEntity<Object> getUsers() {
        log.info("GET /users");
        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        log.info("POST /users");
        return userService.createUser(user);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable long id) {
        log.info("PATCH /users/{}", id);
        return userService.updateUser(id, user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable long id) {
        log.info("DELETE /users/{}", id);
        return userService.deleteUser(id);
    }
}
