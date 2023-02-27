package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.dao.BookingRepository;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingState;
import ru.practicum.shareit.exception.model.ValidationException;
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
    public BookingDto bookingApproveOrDeclined(long bookingId, boolean status, long userId) {
        validate.validate(userId);
        validate.booking(bookingId);
        validate.validateUserOwnItem(userId, bookingRepository.getById(bookingId).getItemId());
        Booking booking = bookingRepository.getById(bookingId);
        validate.validateApproveStatus(booking.getStatus());
        if (status) {
            booking.setStatus(BookingState.APPROVED);
        } else {
            booking.setStatus(BookingState.REJECTED);
        }
        bookingRepository.save(booking);
        return bookingMapper.toBookingDto(booking);
    }

    @Override
    public BookingDto booking(long userId, Booking booking) {
        log.info("iserId = {}, booking = {}", userId, booking);
        validate.validate(userId);
        validate.validateShowItem(booking.getItemId());
        booking.setBookerId(userId);
        validate.validateBookingAvailable(booking);
        booking.setStatus(BookingState.WAITING);
        booking.setItemName(itemRepository.getById(booking.getItemId()).getName());
        Booking bookingFinal = bookingRepository.save(booking);
        return bookingMapper.toBookingDto(bookingFinal);
    }

    @Override
    public BookingDto getBooking(long bookingId, long userId) {
        validate.validate(userId);
        validate.booking(bookingId);
        validate.validateUserOwnItemOrBooker(bookingId, userId);
        return bookingMapper.toBookingDto(bookingRepository.getById(bookingId));
    }

    @Override
    public Collection<BookingDto> getBookingsUserAll(String state, Integer from, Integer size, long userId) {
        validate.validate(userId);
        LocalDateTime ldt = LocalDateTime.now();
        switch (state) {
            case "ALL":
                System.out.println("all bookings: " + bookingRepository.getAll());
                return bookingMapper.manyToBookingDto(bookingRepository.getByBookerId(userId, PageRequest.of((from / size), size)));
            case "CURRENT":
                return bookingMapper.manyToBookingDto(bookingRepository.getByBookerIdAndCurrentTime(userId, ldt, PageRequest.of((from / size), size)));
            case "PAST":
                return bookingMapper.manyToBookingDto(bookingRepository.getByBookerIdAndPastTime(userId, ldt, PageRequest.of((from / size), size)));
            case "FUTURE":
                return bookingMapper.manyToBookingDto(bookingRepository.getByBookerIdAndFutureTime(userId, ldt, PageRequest.of((from / size), size)));
            case "WAITING":
                return bookingMapper.manyToBookingDto(bookingRepository.getByBookerIdAndStatus(userId, state, PageRequest.of((from / size), size)));
            case "REJECTED":
                return bookingMapper.manyToBookingDto(bookingRepository.getByBookerIdAndStatus(userId, state, PageRequest.of((from / size), size)));
            default:
                throw new ValidationException("Unknown state: " + state);
        }
    }

    @Override
    public Collection<BookingDto> getBookingsOwnerAll(String state, Integer from, Integer size, long userId) {
        validate.validate(userId);
        LocalDateTime ldt = LocalDateTime.now();
        switch (state) {
            case "ALL":
                System.out.println("all bookings: " + bookingRepository.getAll());
                return bookingMapper.manyToBookingDto(bookingRepository.getByOwnerId(userId, PageRequest.of(from, size)));
            case "CURRENT":
                return bookingMapper.manyToBookingDto(bookingRepository.getByOwnerIdAndCurrentTime(userId, ldt, PageRequest.of((from / size), size)));
            case "PAST":
                return bookingMapper.manyToBookingDto(bookingRepository.getByOwnerIdAndPastTime(userId, ldt, PageRequest.of((from / size), size)));
            case "FUTURE":
                return bookingMapper.manyToBookingDto(bookingRepository.getByOwnerIdAndFutureTime(userId, ldt, PageRequest.of((from / size), size)));
            case "WAITING":
                return bookingMapper.manyToBookingDto(bookingRepository.getByOwnerIdAndStatus(userId, state, PageRequest.of((from / size), size)));
            case "REJECTED":
                return bookingMapper.manyToBookingDto(bookingRepository.getByOwnerIdAndStatus(userId, state, PageRequest.of((from / size), size)));
            default:
                throw new ValidationException("Unknown state: " + state);
        }
    }
}
