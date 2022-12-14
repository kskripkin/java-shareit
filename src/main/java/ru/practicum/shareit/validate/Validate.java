package ru.practicum.shareit.validate;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.model.ConflictException;
import ru.practicum.shareit.exception.model.NotFoundException;
import ru.practicum.shareit.exception.model.ValidationException;
import ru.practicum.shareit.item.dao.ItemDAO;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.dao.UserDAO;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class Validate {

    private final UserDAO userDAO;
    private final ItemDAO itemDAO;
    private List<User> userList = new ArrayList<>();

    public void validateCreateUser(User user) {
        if (user.getEmail() == null || !EmailValidator.getInstance().isValid(user.getEmail())) {
            throw new ValidationException("Email not valid");
        }
        Stream<User> userStream = userDAO.getUsers().stream();
        userStream.filter(x -> x.getEmail().equals(user.getEmail())).findAny().ifPresent(x -> {
            throw new ConflictException("Duplicate email");
        });
    }

    public void validateUpdateUser(User user) {
        if (user.getEmail() != null && !EmailValidator.getInstance().isValid(user.getEmail())) {
            throw new ValidationException("Email not valid");
        }
        Stream<User> userStream = userDAO.getUsers().stream();
        userStream.filter(x -> x.getEmail().equals(user.getEmail())).findAny().ifPresent(x -> {
            throw new ConflictException("Duplicate email");
        });
    }

    public void validate(int id) {
        Stream<User> userStream = userDAO.getUsers().stream();
        userList = userStream.filter(x -> x.getId() == id).collect(Collectors.toList());
        if (userList.size() == 0) {
            throw new NotFoundException("User not found");
        }
    }

    public void validateItem(Item item) {
        if (item.getAvailable() == null) {
            throw new ValidationException("Available not found");
        }
        if (item.getName() == "") {
            throw new ValidationException("Name is empty");
        }
        if (item.getDescription() == "" || item.getDescription() == null) {
            throw new ValidationException("Description is empty");
        }
    }

    public void validateItemDto(ItemDto itemDto) {
        if (itemDto.getAvailable() == null) {
            throw new ValidationException("Available not found");
        }
        if (itemDto.getName() == "") {
            throw new ValidationException("Name is empty");
        }
        if (itemDto.getDescription() == "" || itemDto.getDescription() == null) {
            throw new ValidationException("Description is empty");
        }
    }

    public void validateUserOwnItem(int userId, int itemId) {
        if (!itemDAO.getMapUsersAndItems().containsKey(userId)) {
            throw new NotFoundException("Item for user not found");
        }
        Stream<Item> itemStream = itemDAO.showItems(userId).stream();
        if (itemStream.filter(x -> x.getId() == itemId).collect(Collectors.toList()).size() == 0) {
            throw new NotFoundException("Item does not belong to the user");
        }
    }
}
