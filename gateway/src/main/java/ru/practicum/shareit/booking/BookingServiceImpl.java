package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.validate.Validate;

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
        validate.bookingTime(bookingDto);
        return bookingClient.bookItem(userId, bookingDto);
    }

    @Override
    public ResponseEntity<Object> getBooking(long bookingId, long userId) {
        validate.validateLong(userId);
        validate.validateLong(bookingId);
        return bookingClient.getBooking(userId, bookingId);
    }

    @Override
    public ResponseEntity<Object> getBookingsUserAll(String state, Integer from, Integer size, long userId) {
        validate.paginationFrom(from);
        return bookingClient.getBookings(userId, state, from, size);
    }

    @Override
    public ResponseEntity<Object> getBookingsOwnerAll(String state, Integer from, Integer size, long userId) {
        validate.paginationFrom(from);
        return bookingClient.getBookingsByOwner(userId, state, from, size);
    }
}
