package ru.practicum.shareit.validate;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.model.ConflictException;
import ru.practicum.shareit.exception.model.NotFoundException;
import ru.practicum.shareit.exception.model.ValidationException;
import ru.practicum.shareit.item.dao.ItemRepository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.dao.UserRepository;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Validate {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private List<User> userList = new ArrayList<>();

    public void validateCreateUser(User user) {
        if (user.getEmail() == null || !EmailValidator.getInstance().isValid(user.getEmail())) {
            throw new ValidationException("Email not valid");
        }
//        if (userRepository.findByEmailContainingIgnoreCase(user.getEmail()).size() != 0) {
//            throw new ConflictException("Duplicate email");
//        }
    }

    public void validateUpdateUser(User user) {
        if (user.getEmail() != null && !EmailValidator.getInstance().isValid(user.getEmail())) {
            throw new ValidationException("Email not valid");
        }
    }

    public void validate(long id) {
        if (userRepository.getById(id) == null) {
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

    public void validateUserOwnItem(long userId, long itemId) {
        if (itemRepository.findByUserIdAndItemId(userId, itemId) == null) {
            throw new NotFoundException("Item does not belong to the user");
        }
    }
}
