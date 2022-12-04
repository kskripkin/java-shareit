package ru.practicum.shareit.booking;

import ru.practicum.shareit.booking.model.Booking;

import java.util.Collection;

public interface BookingService {

    Booking booking(long userId, Booking booking);

    void bookingApproveOrDeclined(long bookingId, String approved, long userId);

    Booking getBooking(long bookingId, long userId);

    Collection<Booking> getBookingsUserAll(String state, long userId);

    Collection<Booking> getBookingsOwnerAll(String state, long userId);
}
