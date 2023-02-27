package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Controller
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Slf4j
@Validated
public class BookingController {
	private final BookingService bookingService;

	@GetMapping
	public ResponseEntity<Object> getBookingsUserAll(@RequestHeader("X-Sharer-User-Id") long userId,
													 @RequestParam(name = "state", defaultValue = "all") String state,
													 @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") Integer from,
													 @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {
		log.info("GET /bookings?from={}&size={}&state={} X-Sharer-User-Id={}", from, size, state, userId);
		return bookingService.getBookingsUserAll(state, from, size, userId);
	}

	@GetMapping("/owner")
	public ResponseEntity<Object> getBookingsOwnerAll(@RequestHeader("X-Sharer-User-Id") long userId,
													  @RequestParam(name = "state", defaultValue = "all") String state,
													  @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") Integer from,
													  @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {
		log.info("GET /bookings/owner?from={}&size={}&state={} X-Sharer-User-Id={}", from, size, state, userId);
		return bookingService.getBookingsOwnerAll(state, from, size, userId);
	}

	@PostMapping
	public ResponseEntity<Object> bookItem(@RequestHeader("X-Sharer-User-Id") long userId,
			@RequestBody @Valid BookingDto requestDto) {
		log.info("POST /bookings {}, userId={}", requestDto, userId);
		return bookingService.booking(userId, requestDto);
	}

	@GetMapping("/{bookingId}")
	public ResponseEntity<Object> getBooking(@RequestHeader("X-Sharer-User-Id") long userId,
			@PathVariable Long bookingId) {
		log.info("GET /bookings/{}, userId={}", bookingId, userId);
		return bookingService.getBooking(bookingId, userId);
	}

	@PatchMapping("/{bookingId}")
	public ResponseEntity<Object> bookingApproveOrDeclined(@PathVariable int bookingId,
											   @RequestParam boolean approved,
											   @RequestHeader("X-Sharer-User-Id") long userId) {
		log.info("PATCH /bookings/{}?approved={} X-Sharer-User-Id={}", bookingId, approved, userId);
		return bookingService.bookingApproveOrDeclined(bookingId, approved, userId);
	}
}
