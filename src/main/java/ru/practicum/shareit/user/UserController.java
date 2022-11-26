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
    private final ValidateUser validateUser;

    @GetMapping
    public User getUser(@RequestBody int id) {
        log.info("GET /users");
        return userService.getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        log.info("POST /users");
        validateUser.validate(user);
        return userService.createUser(user);
    }

    @PatchMapping
    public User updateUser(@RequestBody User user) {
        log.info("PUT /users");
        validateUser.validate(user);
        return userService.updateUser(user);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody int id) {
        log.info("DELETE /users");
        validateUser.validate(id);
        userService.deleteUser(id);
    }
}
