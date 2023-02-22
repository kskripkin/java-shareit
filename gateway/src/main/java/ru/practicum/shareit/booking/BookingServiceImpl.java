package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.BookingState;
import ru.practicum.shareit.exception.model.ValidationException;
import ru.practicum.shareit.validate.Validate;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {
    private final Validate validate;

    private final BookingClient bookingClient;

    @Override
    public ResponseEntity<Object> bookingApproveOrDeclined(long bookingId, boolean status, long userId) {
        validate.validateLong(bookingId);
        validate.validateLong(userId);
        return bookingClient.bookingApproveOrDeclined(bookingId, status, userId);
    }

    @Override
    public ResponseEntity<Object> booking(long userId, BookingDto bookingDto) {
        validate.validateLong(userId);
        return bookingClient.saveBooking(userId, bookingDto);
    }

    @Override
    public ResponseEntity<Object> getBooking(long bookingId, long userId) {
        validate.validateLong(userId);
        validate.validateLong(bookingId);
        return bookingClient.getBooking(userId, bookingId);
    }

    @Override
    public ResponseEntity<Object> getBookingsUserAll(BookingState state, Integer from, Integer size, long userId) {
        validate.paginationFrom(from);
        LocalDateTime ldt = LocalDateTime.now();
        switch (state) {
            case "ALL":
                return bookingClient.getByBookerId(userId, PageRequest.of((from / size), size)));
            case "CURRENT":
                return bookingClient.getByBookerIdAndCurrentTime(userId, ldt, PageRequest.of((from / size), size));
            case "PAST":
                return bookingClient.getByBookerIdAndPastTime(userId, ldt, PageRequest.of((from / size), size));
            case "FUTURE":
                return bookingClient.getByBookerIdAndFutureTime(userId, ldt, PageRequest.of((from / size), size));
            case "WAITING":
                return bookingClient.getByBookerIdAndStatus(userId, state, PageRequest.of((from / size), size));
            case "REJECTED":
                return bookingClient.getByBookerIdAndStatus(userId, state, PageRequest.of((from / size), size));
            default:
                throw new ValidationException("Unknown state: " + state);
        }
    }

    @Override
    public ResponseEntity<Object> getBookingsOwnerAll(BookingState state, Integer from, Integer size, long userId) {
        validate.paginationFrom(from);
        LocalDateTime ldt = LocalDateTime.now();
        switch (state) {
            case "ALL":
                System.out.println("all bookings: " + bookingClient.getAll());
                return bookingMapper.manyToBookingDto(bookingClient.getByOwnerId(userId, PageRequest.of(from, size)));
            case "CURRENT":
                return bookingMapper.manyToBookingDto(bookingClient.getByOwnerIdAndCurrentTime(userId, ldt, PageRequest.of((from / size), size)));
            case "PAST":
                return bookingMapper.manyToBookingDto(bookingClient.getByOwnerIdAndPastTime(userId, ldt, PageRequest.of((from / size), size)));
            case "FUTURE":
                return bookingMapper.manyToBookingDto(bookingClient.getByOwnerIdAndFutureTime(userId, ldt, PageRequest.of((from / size), size)));
            case "WAITING":
                return bookingMapper.manyToBookingDto(bookingClient.getByOwnerIdAndStatus(userId, state, PageRequest.of((from / size), size)));
            case "REJECTED":
                return bookingMapper.manyToBookingDto(bookingClient.getByOwnerIdAndStatus(userId, state, PageRequest.of((from / size), size)));
            default:
                throw new ValidationException("Unknown state: " + state);
        }
    }
}
