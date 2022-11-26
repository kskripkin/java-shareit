package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.model.User;

/**
 * TODO Sprint add-controllers.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public User getUser(@RequestBody int id) {
        log.info("GET /users");
        return userService.getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        log.info("POST /users");
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        log.info("PUT /users");
        return userService.updateUser(user);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody int id) {
        log.info("DELETE /users");
        userService.deleteUser(id);
    }
}
