package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingState;
import ru.practicum.shareit.item.ItemService;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookingServiceImplUnitTest {

    private final BookingService bookingService;
    private final UserService userService;
    private final ItemService itemService;
    private Booking booking;
    private BookingDto bookingDto;
    private Item item;
    private ItemDto itemDto;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setName("Anna");
        user.setEmail("anna@mail.ru");
        userService.createUser(user);

        user = new User();
        user.setId(2);
        user.setName("Anna2");
        user.setEmail("anna2@mail.ru");
        userService.createUser(user);

        itemDto = new ItemDto();
        itemDto.setAvailable(true);
        itemDto.setName("Text");
        itemDto.setDescription("Text");
        itemDto.setRequestId(0L);
        itemDto.setComments(new ArrayList<>());
        itemDto.setId(1);
        itemService.addItem(1, itemDto);

        booking = new Booking();
        booking.setStatus(BookingState.WAITING);
        booking.setItemName("Text");
        LocalDateTime ldt1 = LocalDateTime.now().plusHours(1);
        LocalDateTime ldt2 = LocalDateTime.now().plusHours(2);
        booking.setStart(ldt1);
        booking.setEnd(ldt2);
        booking.setBookerId(2);
        booking.setItemId(1);
        booking.setId(1);

        item = new Item();
        item.setAvailable(true);
        item.setName("Text");
        item.setDescription("Text");
        item.setRequestId(0L);
        item.setId(1);
        item.setOwnerId(1);

        bookingDto = new BookingDto(1, ldt1, ldt2, user, BookingState.WAITING, item);
    }

    @Order(1)
    @Test
    @Transactional
    void booking() {
        assertEquals(bookingService.booking(2, booking), bookingDto);
    }
}