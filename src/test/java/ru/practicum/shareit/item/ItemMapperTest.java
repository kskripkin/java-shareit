package ru.practicum.shareit.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.booking.config.ConfigDateTime;
import ru.practicum.shareit.booking.dao.BookingRepository;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingState;
import ru.practicum.shareit.item.dao.CommentsRepository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.LastBooking;
import ru.practicum.shareit.item.dto.NextBooking;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;

import java.time.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ItemMapperTest {

    @InjectMocks
    private ItemMapper itemMapper;

    @Mock
    private ConfigDateTime configDateTime;
    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private CommentsRepository commentsRepository;

    private Booking bookingOne;
    private LocalDateTime localDateTime;
    private Item item;
    private ItemDto itemDto;
    private Comment comment;
    private ArrayList<Comment> commentArrayList;
    private LastBooking lastBooking;

    private NextBooking nextBooking;

    @BeforeEach
    void setUp() {
        localDateTime = LocalDateTime.now();
        item = new Item();
        item.setAvailable(true);
        item.setName("Text");
        item.setDescription("Text");
        item.setRequestId(1L);
        item.setId(1);
        item.setOwnerId(1);

        itemDto = new ItemDto();
        itemDto.setAvailable(true);
        itemDto.setName("Text");
        itemDto.setDescription("Text");
        itemDto.setRequestId(1L);
        itemDto.setId(1);
        lastBooking = new LastBooking();
        lastBooking.setId(1L);
        lastBooking.setBookerId(1L);
        itemDto.setLastBooking(lastBooking);
        nextBooking = new NextBooking();
        nextBooking.setId(1L);
        nextBooking.setBookerId(1L);
        itemDto.setNextBooking(nextBooking);

        comment = new Comment();
        comment.setText("Text");
        comment.setItemId(1);
        comment.setAuthorName("Text");
        comment.setCreated(localDateTime);
        comment.setId(1);


        commentArrayList = new ArrayList<>();
        commentArrayList.add(comment);
        itemDto.setComments(commentArrayList);

        bookingOne = new Booking(1, localDateTime, localDateTime, 1, 1, BookingState.WAITING, "Text");
   }

    @Test
    void toItemDto() {

        when(configDateTime.getLocalDateTime()).thenReturn(localDateTime);
        when(bookingRepository.getByItemIdLast(1, localDateTime, 1)).thenReturn(bookingOne);
        when(bookingRepository.getByItemIdNext(1, localDateTime, 1)).thenReturn(bookingOne);
        when(commentsRepository.getByItemId(1)).thenReturn(commentArrayList);

        assertEquals(itemMapper.toItemDto(1, item), itemDto);

        when(bookingRepository.getByItemIdLast(1, localDateTime, 1)).thenReturn(null);
        when(bookingRepository.getByItemIdNext(1, localDateTime, 1)).thenReturn(null);
        itemDto.setNextBooking(null);
        itemDto.setLastBooking(null);

        assertEquals(itemMapper.toItemDto(1, item), itemDto);

        itemDto.setNextBooking(null);
        itemDto.setLastBooking(lastBooking);
        when(bookingRepository.getByItemIdLast(1, localDateTime, 1)).thenReturn(bookingOne);
        when(bookingRepository.getByItemIdNext(1, localDateTime, 1)).thenReturn(null);

        assertEquals(itemMapper.toItemDto(1, item), itemDto);

        itemDto.setNextBooking(nextBooking);
        itemDto.setLastBooking(null);
        when(bookingRepository.getByItemIdLast(1, localDateTime, 1)).thenReturn(null);
        when(bookingRepository.getByItemIdNext(1, localDateTime, 1)).thenReturn(bookingOne);

        assertEquals(itemMapper.toItemDto(1, item), itemDto);

    }

    @Test
    void toItem() {
        item.setId(0);
        item.setOwnerId(0);
        assertEquals(itemMapper.toItem(itemDto), item);

        itemDto.setRequestId(null);
        item.setRequestId(null);
        assertEquals(itemMapper.toItem(itemDto), item);
    }
}