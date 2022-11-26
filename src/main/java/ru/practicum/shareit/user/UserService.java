package ru.practicum.shareit.user;

import ru.practicum.shareit.user.model.User;

public interface UserService {

    User getUser(int id);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(int id);
}
