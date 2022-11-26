package ru.practicum.shareit.user.dao;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.user.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserDAO {

    private static Map<Integer, User> usersMap = new HashMap<>();
    private static int userId = 1;

    public User getUser(int id) {
        return usersMap.get(id);
    }

    public Collection<User> getUsers() {
        return usersMap.values();
    }

    public User createUser(User user) {
        user.setId(userId++);
        usersMap.put(user.getId(), user);
        return usersMap.get(user.getId());
    }

    public User updateUser(User user) {
        usersMap.put(user.getId(), user);
        return usersMap.get(user.getId());
    }

    public void deleteUser(int id) {
        usersMap.remove(id);
    }
}
