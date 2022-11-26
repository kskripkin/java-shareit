package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.model.NotFoundException;
import ru.practicum.shareit.exception.model.ValidationException;
import ru.practicum.shareit.user.dao.UserDAO;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class ValidateUser {

    private final UserDAO userDAO;
    private List<User> userList = new ArrayList<>();

    public void validate(User user) {
        if (user.getEmail() == null) {
            throw new ValidationException("Email not found");
        }
        if (EmailValidator.getInstance().isValid(user.getEmail())) {
            throw new ValidationException("Email not valid");
        }
        Stream<User> userStream = userDAO.getUsers().stream();
        userStream.filter(x -> x.getEmail().equals(user.getEmail())).findAny().ifPresent(x -> {
            throw new ValidationException("Duplicate email");
        });
    }

    public void validate(int id) {
        Stream<User> userStream = userDAO.getUsers().stream();
        userList = userStream.filter(x -> x.getId() == id).collect(Collectors.toList());
        if (userList.size() == 0) {
            throw new NotFoundException("User not found");
        }
    }
}
