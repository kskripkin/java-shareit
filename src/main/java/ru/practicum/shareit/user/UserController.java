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

    @GetMapping("{id}")
    public User getUser(@PathVariable int id) {
        log.info("GET /users");
        return userService.getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        log.info("POST /users");
        return userService.createUser(user);
    }

    @PatchMapping("{id}")
    public User updateUser(@RequestBody User user, @PathVariable int id) {
        log.info("PATCH /users");
        return userService.updateUser(id, user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable int id) {
        log.info("DELETE /users");
        userService.deleteUser(id);
    }
}
