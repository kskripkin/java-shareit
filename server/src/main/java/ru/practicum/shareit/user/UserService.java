package ru.practicum.shareit.user;

import ru.practicum.shareit.user.model.User;

import java.util.Collection;

public interface UserService {

    User getUser(long id);

    Collection<User> getUsers();

    User createUser(User user);

    User updateUser(long id, User user);

    void deleteUser(long id);
}
