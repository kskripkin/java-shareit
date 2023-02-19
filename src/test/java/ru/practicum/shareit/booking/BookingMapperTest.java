package ru.practicum.shareit.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingState;
import ru.practicum.shareit.item.dao.ItemRepository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.dao.UserRepository;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingMapperTest {

    @InjectMocks
    private BookingMapper bookingMapper;

    @Mock
    private UserRepository userRepository;
    @Mock
    private ItemRepository itemRepository;
    private User user;
    private Item item;
    private Booking booking;
    private BookingDto bookingDto;
    private ArrayList<BookingDto> bookingDtoArrayList;
    private ArrayList<Booking> bookingArrayList;

    @BeforeEach
    void setUp() {
        item = new Item();
        item.setAvailable(true);
        item.setName("Text");
        item.setDescription("Text");
        item.setRequestId(1L);
        item.setId(1);
        item.setOwnerId(1);

        user = new User();
        user.setId(1);
        user.setName("Anna");
        user.setEmail("anna@mail.ru");

        booking = new Booking();
        booking.setStatus(BookingState.WAITING);
        booking.setItemName("Text");
        LocalDateTime ldt1 = LocalDateTime.now().plusHours(1);
        LocalDateTime ldt2 = LocalDateTime.now().plusHours(2);
        booking.setStart(ldt1);
        booking.setEnd(ldt2);
        booking.setBookerId(1);
        booking.setItemId(1);
        booking.setId(1);

        bookingDto = new BookingDto(1, ldt1, ldt2, user, BookingState.WAITING, item);
        bookingDtoArrayList = new ArrayList<>();
        bookingDtoArrayList.add(bookingDto);
        bookingArrayList = new ArrayList<>();
        bookingArrayList.add(booking);

    }

    @Test
    void toBookingDto() {
        when(userRepository.getById(1L)).thenReturn(user);
        when(itemRepository.getById(1L)).thenReturn(item);

        assertEquals(bookingMapper.toBookingDto(booking), bookingDto);
    }

    @Test
    void manyToBookingDto() {
        bookingDto.setBooker(null);
        bookingDto.setItem(null);
        assertEquals(bookingMapper.manyToBookingDto(bookingArrayList), bookingDtoArrayList);
    }
}