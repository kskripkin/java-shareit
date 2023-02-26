package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.config.ConfigDateTime;
import ru.practicum.shareit.booking.dao.BookingRepository;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.dao.CommentsRepository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.LastBooking;
import ru.practicum.shareit.item.dto.NextBooking;
import ru.practicum.shareit.item.model.Item;

@Service
@RequiredArgsConstructor
public class ItemMapper {

    private final BookingRepository bookingRepository;
    private final CommentsRepository commentsRepository;
    private final ConfigDateTime configDateTime;

    public ItemDto toItemDto(long userId, Item item) {
        Booking bookingLast = bookingRepository.getByItemIdLast(item.getId(), configDateTime.getLocalDateTime(), userId);
        Booking bookingNext = bookingRepository.getByItemIdNext(item.getId(), configDateTime.getLocalDateTime(), userId);
        if (bookingLast != null && bookingNext != null) {
            return new ItemDto(
                    item.getId(),
                    item.getName(),
                    item.getDescription(),
                    item.getAvailable(),
                    new LastBooking(bookingLast.getId(), bookingLast.getBookerId()),
                    new NextBooking(bookingNext.getId(), bookingNext.getBookerId()),
                    commentsRepository.getByItemId(item.getId()),
                    item.getRequestId()
            );
        } else if (bookingLast == null && bookingNext == null) {
            return new ItemDto(
                    item.getId(),
                    item.getName(),
                    item.getDescription(),
                    item.getAvailable(),
                    null,
                    null,
                    commentsRepository.getByItemId(item.getId()),
                    item.getRequestId()
            );
        } else if (bookingLast != null && bookingNext == null) {
            return new ItemDto(
                    item.getId(),
                    item.getName(),
                    item.getDescription(),
                    item.getAvailable(),
                    new LastBooking(bookingLast.getId(), bookingLast.getBookerId()),
                    null,
                    commentsRepository.getByItemId(item.getId()),
                    item.getRequestId()
            );
        } else {
            return new ItemDto(
                    item.getId(),
                    item.getName(),
                    item.getDescription(),
                    item.getAvailable(),
                    null,
                    new NextBooking(bookingNext.getId(), bookingNext.getBookerId()),
                    commentsRepository.getByItemId(item.getId()),
                    item.getRequestId()
            );
        }
    }

    public Item toItem(ItemDto itemDto) {
        if (itemDto.getRequestId() != null) {
            return new Item(
                    itemDto.getName(),
                    itemDto.getDescription(),
                    itemDto.getAvailable(),
                    itemDto.getRequestId()
            );
        } else {
            return new Item(
                    itemDto.getName(),
                    itemDto.getDescription(),
                    itemDto.getAvailable(),
                    null
            );
        }
    }
}
