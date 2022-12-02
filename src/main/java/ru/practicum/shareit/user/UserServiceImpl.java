package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dao.UserDAO;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.validate.Validate;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final Validate validate;

    @Override
    public User getUser(int id) {
        validate.validate(id);
        return userDAO.getUser(id);
    }

    @Override
    public Collection<User> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    public User createUser(User user) {
        validate.validateCreateUser(user);
        return userDAO.createUser(user);
    }

    @Override
    public User updateUser(int id, User user) {
        validate.validateUpdateUser(user);
        return userDAO.updateUser(id, user);
    }

    @Override
    public void deleteUser(int id) {
        validate.validate(id);
        userDAO.deleteUser(id);
    }
}
