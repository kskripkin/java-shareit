package ru.practicum.shareit.validate;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.dao.BookingRepository;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingState;
import ru.practicum.shareit.exception.model.NotFoundException;
import ru.practicum.shareit.exception.model.ValidationException;
import ru.practicum.shareit.item.dao.ItemRepository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.dao.UserRepository;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class Validate {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final BookingRepository bookingRepository;

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
        if (userRepository.findById(id).isEmpty()) {
            throw new NotFoundException("User not found");
        }
    }

    public void validateShowItem(long id) {
        if (itemRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Item not found");
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

    public void validateUserOwnItemOrBooker(long bookingId, long userId) {
        if (bookingRepository.getById(bookingId).getBookerId() == userId ||
                itemRepository.getById(bookingRepository.getById(bookingId).getItemId()).getOwnerId() == userId) {
            System.out.println("OK");
        } else {
            throw new NotFoundException("User has nothing to do with the thing");
        }
    }

    public void booking(long bookingId) {
        if (bookingRepository.findById(bookingId).isEmpty()) {
            throw new NotFoundException("Booking not found");
        }
    }

    public void validateBookingAvailable(Booking booking) {
        if (!itemRepository.getById(booking.getItemId()).getAvailable()) {
            throw new ValidationException("Item not available");
        }
        System.out.println("qqqqqqqqqq " + bookingRepository.getByItemIdAndTime(booking.getItemId(), booking.getStart(), booking.getEnd()));
        System.out.println("wwwwwwwwww " + bookingRepository.getAll());
        if (!bookingRepository.getByItemIdAndTime(booking.getItemId(), booking.getStart(), booking.getEnd()).isEmpty()) {
            throw new NotFoundException("Timeslot already using");
        }
        System.out.println("llllllllllll" + bookingRepository.getByItemIdAndUserId(booking.getItemId(), booking.getBookerId()));
//        if (!bookingRepository.getByItemIdAndUserId(booking.getItemId(), booking.getBookerId()).isEmpty()) {
//            throw new NotFoundException("User already booking this item");
//        }
        System.out.println("booking " + bookingRepository.getAll());
        System.out.println("item " + itemRepository.getAll());
        System.out.println("user " + userRepository.getAll());
        if (itemRepository.getById(booking.getItemId()).getOwnerId() == booking.getBookerId()) {
            throw new NotFoundException("User cannot book his item");
        }
    }

    public void bookingTime(Booking booking) {
        if (booking.getEnd().isBefore(booking.getStart())) {
            throw new ValidationException("End time before start time");
        }
        if (booking.getEnd().isBefore(LocalDateTime.now()) || booking.getStart().isBefore(LocalDateTime.now().minusMinutes(1))) {
            throw new ValidationException("End or start time before now");
        }
    }

    public void validateApproveStatus(BookingState status) {
        if (status == BookingState.APPROVED) {
            throw new ValidationException("Status has already been APPROVED");
        }
    }

    public void validateUserBookingItem(long userId, long itemId) {
        if (bookingRepository.getByItemIdLast(userId, itemId, LocalDateTime.now()).stream().count() == 0) {
            throw new ValidationException("Booking last time not found");
        }
    }

    public void validateBookingLastTime(long userId, long itemId) {
        if (bookingRepository.getByItemId(userId, itemId).stream().count() == 0) {
            throw new ValidationException("Booking from user not found");
        }
    }

    public void validateComment(long userId, Comment comment) {
        if (comment.getText().equals("")) {
            throw new ValidationException("Text comment not maybe empty");
        }
    }
}
