package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.dao.BookingRepository;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.dao.ItemRepository;
import ru.practicum.shareit.validate.Validate;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private final ItemRepository itemRepository;
    private final BookingRepository bookingRepository;
    private final Validate validate;
    private int integerUserId;

    @Override
    @Transactional
    public void booking(String userId, Booking booking) {
        integerUserId = Integer.parseInt(userId);
        validate.validate(integerUserId);
        Booking bookingFinal = bookingRepository.save(booking);
        itemRepository.booking(bookingFinal.getId(), bookingFinal.getItemId());
    }
}
