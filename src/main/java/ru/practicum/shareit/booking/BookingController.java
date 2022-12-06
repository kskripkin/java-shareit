package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingDto booking(@RequestBody Booking booking, @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("POST /bookings X-Sharer-User-Id={}", userId);
        return bookingService.booking(userId, booking);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto bookingApproveOrDeclined(@PathVariable int bookingId, @RequestParam boolean approved, @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("PATCH /bookings/{}?approved={} X-Sharer-User-Id={}", bookingId, approved, userId);
        bookingService.bookingApproveOrDeclined(bookingId, approved, userId);
        BookingDto booking = getBooking(bookingId, userId);
        return getBooking(bookingId, userId);
    }

    @GetMapping("/{bookingId}")
    public BookingDto getBooking(@PathVariable int bookingId, @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /bookings/{} X-Sharer-User-Id={}", bookingId, userId);
        return bookingService.getBooking(bookingId, userId);
    }

    @GetMapping
    public Collection<Booking> getBookingsUserAll(@RequestParam(required = false, defaultValue = "ALL") String state, @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /bookings?state= X-Sharer-User-Id={}", state, userId);
        return bookingService.getBookingsUserAll(state, userId);
    }

    @GetMapping("/owner")
    public Collection<Booking> getBookingsOwnerAll(@RequestParam(required = false, defaultValue = "ALL") String state, @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /bookings?state= X-Sharer-User-Id={}", state, userId);
        return bookingService.getBookingsOwnerAll(state, userId);
    }
}
