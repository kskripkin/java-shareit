package ru.practicum.shareit.booking;

import org.springframework.http.ResponseEntity;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.BookingState;

import java.util.Collection;

public interface BookingService {

    ResponseEntity<Object> booking(long userId, BookingDto bookingDto);

    ResponseEntity<Object> bookingApproveOrDeclined(long bookingId, boolean approved, long userId);

    ResponseEntity<Object> getBooking(long bookingId, long userId);

    ResponseEntity<Object> getBookingsUserAll(BookingState state, Integer from, Integer size, long userId);

    ResponseEntity<Object> getBookingsOwnerAll(BookingState state, Integer from, Integer size, long userId);
}
