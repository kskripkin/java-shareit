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
        return bookingService.bookingApproveOrDeclined(bookingId, approved, userId);
    }

    @GetMapping("/{bookingId}")
    public BookingDto getBooking(@PathVariable long bookingId, @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /bookings/{} X-Sharer-User-Id={}", bookingId, userId);
        return bookingService.getBooking(bookingId, userId);
    }

    @GetMapping
    public Collection<BookingDto> getBookingsUserAll(@RequestParam(required = false, defaultValue = "ALL") String state,
                                                     @RequestParam(required = false, defaultValue = "0") Integer from,
                                                     @RequestParam(required = false, defaultValue = "10") Integer size,
                                                     @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /bookings?from={}&size={}&state={} X-Sharer-User-Id={}", from, size, state, userId);
        return bookingService.getBookingsUserAll(state, from, size, userId);
    }

    @GetMapping("/owner")
    public Collection<BookingDto> getBookingsOwnerAll(@RequestParam(required = false, defaultValue = "ALL") String state,
                                                      @RequestParam(required = false, defaultValue = "0") Integer from,
                                                      @RequestParam(required = false, defaultValue = "10") Integer size,
                                                      @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /bookings/owner?from={}&size={}&state={} X-Sharer-User-Id={}", from, size, state, userId);
        return bookingService.getBookingsOwnerAll(state, from, size, userId);
    }
}
