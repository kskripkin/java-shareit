package ru.practicum.shareit.validate;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.BookingState;
import ru.practicum.shareit.exception.model.ValidationException;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class Validate {

    public void validateCreateUser(User user) {
        if (user.getEmail() == null || !EmailValidator.getInstance().isValid(user.getEmail())) {
            throw new ValidationException("Email not valid");
        }
    }

    public void validateUpdateUser(User user) {
        if (user.getEmail() != null && !EmailValidator.getInstance().isValid(user.getEmail())) {
            throw new ValidationException("Email not valid");
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

    public void bookingTime(BookingDto booking) {
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

    public void validateLong(long var) {
        if (var < 1) {
            throw new ValidationException("Id < 1");
        }
    }

    public void validateComment(CommentDto comment) {
        if (comment.getText().equals("")) {
            throw new ValidationException("Text comment not maybe empty");
        }
    }

    public void validateItemRequests(ItemRequestDto itemRequest) {
        if (itemRequest.getDescription() == "" || itemRequest.getDescription() == null) {
            throw new ValidationException("Description is empty");
        }
    }

    public void paginationFrom(Integer from) {
        if (from < 0) {
            throw new ValidationException("From < 0");
        }
    }
}
