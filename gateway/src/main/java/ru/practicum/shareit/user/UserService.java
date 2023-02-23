package ru.practicum.shareit.user;

import org.springframework.http.ResponseEntity;
import ru.practicum.shareit.user.model.User;

import java.util.Collection;

public interface UserService {

    ResponseEntity<Object> getUser(long id);

    ResponseEntity<Object> getUsers();

    ResponseEntity<Object> createUser(User user);

    ResponseEntity<Object> updateUser(long id, User user);

    ResponseEntity<Object> deleteUser(long id);
}
