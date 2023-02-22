package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.dao.ItemRepository;
import ru.practicum.shareit.user.dao.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class BookingMapper {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public BookingDto toBookingDto(Booking booking) {
        return new BookingDto(
                booking.getId(),
                booking.getStart(),
                booking.getEnd(),
                userRepository.getById(booking.getBookerId()),
                booking.getStatus(),
                itemRepository.getById(booking.getItemId())
        );
    }

    public Collection<BookingDto> manyToBookingDto(Collection<Booking> booking) {
        Stream<Booking> bookingStream = booking.stream();
        return bookingStream.map(x -> toBookingDto(x)).collect(Collectors.toList());
    }
}
