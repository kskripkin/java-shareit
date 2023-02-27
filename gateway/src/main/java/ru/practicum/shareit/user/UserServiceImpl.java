package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.validate.Validate;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final Validate validate;

    private final UserClient userClient;

    @Override
    public ResponseEntity<Object> getUser(long userId) {
        validate.validateLong(userId);
        return userClient.getUserById(userId);
    }

    @Override
    public ResponseEntity<Object> getUsers() {
        return userClient.findAll();
    }

    @Override
    public ResponseEntity<Object> createUser(User user) {
        validate.validateCreateUser(user);
        return userClient.saveUser(user);
    }

    @Override
    public ResponseEntity<Object> updateUser(long userId, User user) {
        validate.validateLong(userId);
        validate.validateUpdateUser(user);
        return userClient.updateUser(userId, user);
    }

    @Override
    public ResponseEntity<Object> deleteUser(long userId) {
        validate.validateLong(userId);
        return userClient.deleteById(userId);
    }
}
