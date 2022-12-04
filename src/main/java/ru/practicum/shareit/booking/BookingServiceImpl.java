package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.dao.BookingRepository;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingState;
import ru.practicum.shareit.exception.model.NotFoundException;
import ru.practicum.shareit.item.dao.ItemRepository;
import ru.practicum.shareit.validate.Validate;

import java.time.LocalDateTime;
import java.util.Collection;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private final ItemRepository itemRepository;
    private final BookingRepository bookingRepository;
    private final Validate validate;

    @Override
    @Transactional
    public Booking booking(long userId, Booking booking) {
        validate.validate(userId);
        validate.validateShowItem(booking.getItemId());
        validate.validateBookingAvailable(booking.getItemId());
        booking.setStatus(BookingState.WAITING);
        Booking bookingFinal = bookingRepository.save(booking);
        itemRepository.booking(bookingFinal.getId(), bookingFinal.getItemId());
        return bookingFinal;
    }

    @Override
    public void bookingApproveOrDeclined(long bookingId, String approved, long userId) {
        validate.validate(userId);
        validate.booking(bookingId);
        validate.validateUserOwnItem(userId, bookingRepository.getById(bookingId).getItemId());
        bookingRepository.changeStatus(approved, bookingId);
        if (approved.equals(BookingState.APPROVED)) {
            itemRepository.changeAvailable(false, bookingRepository.getById(bookingId).getItemId());
        }
    }

    @Override
    public Booking getBooking(long bookingId, long userId) {
        validate.validate(userId);
        validate.booking(bookingId);
        validate.validateUserOwnItemOrBooker(bookingId, userId);
        return bookingRepository.getById(bookingId);
    }

    @Override
    public Collection<Booking> getBookingsUserAll(String state, long userId) {
        validate.validate(userId);
        switch (state) {
            case "ALL":
                return bookingRepository.getByBookerId(userId);
            case "CURRENT":
                return bookingRepository.getByBookerIdAndCurrentTime(userId, LocalDateTime.now());
            case "PAST":
                return bookingRepository.getByBookerIdAndPastTime(userId, LocalDateTime.now());
            case "FUTURE":
                return bookingRepository.getByBookerIdAndFutureTime(userId, LocalDateTime.now());
            case "WAITING":
                return bookingRepository.getByBookerIdAndStatus(userId, state);
            case "REJECTED":
                return bookingRepository.getByBookerIdAndStatus(userId, state);
            default:
                throw new NotFoundException("State not found");
        }
    }

    @Override
    public Collection<Booking> getBookingsOwnerAll(String state, long userId) {
        validate.validate(userId);
        switch (state) {
            case "ALL":
                return bookingRepository.getByOwnerId(userId);
            case "CURRENT":
                return bookingRepository.getByOwnerIdAndCurrentTime(userId, LocalDateTime.now());
            case "PAST":
                return bookingRepository.getByOwnerIdAndPastTime(userId, LocalDateTime.now());
            case "FUTURE":
                return bookingRepository.getByOwnerIdAndFutureTime(userId, LocalDateTime.now());
            case "WAITING":
                return bookingRepository.getByOwnerIdAndStatus(userId, state);
            case "REJECTED":
                return bookingRepository.getByOwnerIdAndStatus(userId, state);
            default:
                throw new NotFoundException("State not found");
        }
    }
}
