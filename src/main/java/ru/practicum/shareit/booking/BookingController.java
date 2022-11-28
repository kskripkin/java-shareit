package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.model.Booking;

/**
 * TODO Sprint add-bookings.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public void booking(@RequestBody Booking booking, @RequestHeader("X-Sharer-User-Id") String userId) {
        log.info("PATCH /items X-Sharer-User-Id={}", userId);
        bookingService.booking(userId, booking);
    }
}
