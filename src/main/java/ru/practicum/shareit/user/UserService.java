package ru.practicum.shareit.user;

import ru.practicum.shareit.user.model.User;

import java.util.Collection;

public interface UserService {

    User getUser(int id);

    Collection<User> getUsers();

    User createUser(User user);

    User updateUser(int id, User user);

    void deleteUser(int id);
}
