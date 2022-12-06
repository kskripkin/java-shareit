package ru.practicum.shareit.booking;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;

import java.util.Collection;

public interface BookingService {

    BookingDto booking(long userId, Booking booking);

    void bookingApproveOrDeclined(long bookingId, boolean approved, long userId);

    BookingDto getBooking(long bookingId, long userId);

    Collection<Booking> getBookingsUserAll(String state, long userId);

    Collection<Booking> getBookingsOwnerAll(String state, long userId);
}
