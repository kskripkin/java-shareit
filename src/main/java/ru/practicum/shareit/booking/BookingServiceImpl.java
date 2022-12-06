package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.dao.BookingRepository;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingState;
import ru.practicum.shareit.exception.model.NotFoundException;
import ru.practicum.shareit.item.dao.ItemRepository;
import ru.practicum.shareit.validate.Validate;

import java.time.LocalDateTime;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private final ItemRepository itemRepository;
    private final BookingRepository bookingRepository;
    private final Validate validate;
    private final BookingMapper bookingMapper;

    @Override
    @Transactional
    public BookingDto booking(long userId, Booking booking) {
        validate.validate(userId);
        validate.validateShowItem(booking.getItemId());
        validate.validateBookingAvailable(booking.getItemId());
        validate.bookingTime(booking);
        booking.setStatus(BookingState.WAITING);
        booking.setBookerId(userId);
        booking.setItemName(itemRepository.getById(booking.getItemId()).getName());
        Booking bookingFinal = bookingRepository.save(booking);
        itemRepository.booking(bookingFinal.getId(), bookingFinal.getItemId());
        return bookingMapper.toBookingDto(bookingFinal);
    }

    @Override
    @Transactional
    public void bookingApproveOrDeclined(long bookingId, boolean status, long userId) {
        validate.validate(userId);
        validate.booking(bookingId);
        validate.validateUserOwnItem(userId, bookingRepository.getById(bookingId).getItemId());
        if (status == true) {
            bookingRepository.changeStatus(BookingState.APPROVED.toString(), bookingId);
            itemRepository.changeAvailable(false, bookingRepository.getById(bookingId).getItemId());
        }
    }

    @Override
    public BookingDto getBooking(long bookingId, long userId) {
        validate.validate(userId);
        validate.booking(bookingId);
        validate.validateUserOwnItemOrBooker(bookingId, userId);
        return bookingMapper.toBookingDto(bookingRepository.getById(bookingId));
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
