package ru.practicum.shareit.booking;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;

import java.util.Collection;

public interface BookingService {

    BookingDto booking(long userId, Booking booking);

    BookingDto bookingApproveOrDeclined(long bookingId, boolean approved, long userId);

    BookingDto getBooking(long bookingId, long userId);

    Collection<BookingDto> getBookingsUserAll(String state, long userId);

    Collection<BookingDto> getBookingsOwnerAll(String state, long userId);
}
