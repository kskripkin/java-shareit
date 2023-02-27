package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.model.User;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping("{id}")
    public User getUser(@PathVariable long id) {
        log.info("GET /users/{}", id);
        return userService.getUser(id);
    }

    @GetMapping
    public Collection<User> getUsers() {
        log.info("GET /users");
        return userService.getUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        log.info("POST /users");
        return userService.createUser(user);
    }

    @PatchMapping("{id}")
    public User updateUser(@RequestBody User user, @PathVariable long id) {
        log.info("PATCH /users/{}", id);
        User user1 = userService.updateUser(id, user);
        return user1;
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id) {
        log.info("DELETE /users/{}", id);
        userService.deleteUser(id);
    }
}
