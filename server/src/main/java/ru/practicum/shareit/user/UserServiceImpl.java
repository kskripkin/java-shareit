package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dao.UserRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.validate.Validate;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Validate validate;

    @Override
    public User getUser(long id) {
        validate.validate(id);
        return userRepository.getById(id);
    }

    @Override
    public Collection<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(long id, User user) {
        user.setId(id);
        User userSource = userRepository.getById(id);
        if (user.getName() == null) {
            user.setName(userSource.getName());
        }
        if (user.getEmail() == null) {
            user.setEmail(userSource.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        validate.validate(id);
        userRepository.deleteById(id);
    }
}
