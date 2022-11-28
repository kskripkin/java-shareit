package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.dao.ItemDAO;
import ru.practicum.shareit.validate.Validate;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService{

    private final ItemDAO itemDAO;
    private final Validate validate;
    private int integerUserId;

    @Override
    public void booking(String userId, Booking booking) {
        integerUserId = Integer.parseInt(userId);
        validate.validate(integerUserId);
        itemDAO.booking(booking);
    }
}
