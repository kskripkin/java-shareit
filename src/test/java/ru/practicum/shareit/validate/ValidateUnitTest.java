package ru.practicum.shareit.validate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.booking.dao.BookingRepository;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingState;
import ru.practicum.shareit.exception.model.NotFoundException;
import ru.practicum.shareit.exception.model.ValidationException;
import ru.practicum.shareit.item.dao.ItemRepository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.dao.ItemRequestRepository;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.dao.UserRepository;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateUnitTest {

    @InjectMocks
    private Validate validate;

    @Mock
    private UserRepository userRepository;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private ItemRequestRepository itemRequestRepository;

    private User user;
    private User userFail;
    private Booking booking;
    private Item item;
    private Comment comment;
    private ItemRequest itemRequest;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setEmail("anna@mail.con");
        user.setName("Anna");

        userFail = new User();
        userFail.setId(-1);
        userFail.setEmail("");
        userFail.setName("");

        booking = new Booking();
        booking.setId(1);
        booking.setBookerId(1);
        booking.setItemId(1);

        item = new Item();
        item.setOwnerId(1);
        item.setId(1);
        item.setAvailable(false);

        comment = new Comment();
        comment.setText("");

        itemRequest = new ItemRequest();
        itemRequest.setDescription("");
        itemRequest.setId(1);
    }

    @Test
    void validateCreateUser() {
        assertThrows(ValidationException.class, () -> validate.validateCreateUser(userFail));
    }

    @Test
    void validateUpdateUser() {
        assertThrows(ValidationException.class, () -> validate.validateUpdateUser(userFail));
    }

    @Test
    void validate() {
        assertThrows(NotFoundException.class, () -> validate.validate(1));
    }

    @Test
    void validateShowItem() {
        assertThrows(NotFoundException.class, () -> validate.validateShowItem(1));
    }

    @Test
    void validateItemDto() {
        ItemDto itemDto = new ItemDto();
        assertThrows(ValidationException.class, () -> validate.validateItemDto(itemDto));
        itemDto.setAvailable(true);
        itemDto.setName("");
        assertThrows(ValidationException.class, () -> validate.validateItemDto(itemDto));
        itemDto.setName("Anna");
        itemDto.setDescription("");
        assertThrows(ValidationException.class, () -> validate.validateItemDto(itemDto));

    }

    @Test
    void validateUserOwnItem() {
        assertThrows(NotFoundException.class, () -> validate.validateUserOwnItem(1, 1));
    }

    @Test
    void validateUserOwnItemOrBooker() {
        when(bookingRepository.getById(any())).thenReturn(booking);
        when(itemRepository.getById(any())).thenReturn(item);

        assertThrows(NotFoundException.class, () -> validate.validateUserOwnItemOrBooker(1, 2));
    }

    @Test
    void booking() {
        assertThrows(NotFoundException.class, () -> validate.booking(1));
    }

    @Test
    void validateBookingAvailable() {
        ArrayList<Booking> arrayListB = new ArrayList<>();
        arrayListB.add(new Booking());
        when(itemRepository.getById(any())).thenReturn(item);

        assertThrows(ValidationException.class, () -> validate.validateBookingAvailable(booking));

        item.setAvailable(true);
        when(bookingRepository.getByItemIdAndTime(1, booking.getStart(), booking.getEnd())).thenReturn(arrayListB);

        assertThrows(NotFoundException.class, () -> validate.validateBookingAvailable(booking));

        when(bookingRepository.getByItemIdAndTime(1, booking.getStart(), booking.getEnd())).thenReturn(new ArrayList<>());
        item.setOwnerId(1);
        booking.setBookerId(1);

        assertThrows(NotFoundException.class, () -> validate.validateBookingAvailable(booking));


    }

    @Test
    void bookingTime() {
        booking.setEnd(LocalDateTime.now().minusMinutes(2));
        booking.setStart(LocalDateTime.now().minusMinutes(1));
        assertThrows(ValidationException.class, () -> validate.bookingTime(booking));

        booking.setEnd(LocalDateTime.now().minusMinutes(1));
        booking.setStart(LocalDateTime.now().minusMinutes(2));
        assertThrows(ValidationException.class, () -> validate.bookingTime(booking));
    }

    @Test
    void validateApproveStatus() {
        booking.setStatus(BookingState.APPROVED);
        assertThrows(ValidationException.class, () -> validate.validateApproveStatus(BookingState.APPROVED));
    }

    @Test
    void validateUserBookingItem() {
       assertThrows(ValidationException.class, () -> validate.validateUserBookingItem(1, 1));
    }

    @Test
    void validateBookingLastTime() {
        assertThrows(ValidationException.class, () -> validate.validateBookingLastTime(1, 1));
    }

    @Test
    void validateComment() {
        assertThrows(ValidationException.class, () -> validate.validateComment(1, comment));
    }

    @Test
    void validateItemRequests() {
        assertThrows(ValidationException.class, () -> validate.validateItemRequests(itemRequest));
    }

    @Test
    void validateItemRequestsId() {
        List<ItemRequest> itemRequests = new ArrayList<>();
        itemRequests.add(itemRequest);
        when(itemRequestRepository.findById(1)).thenReturn(itemRequests);

        assertThrows(NotFoundException.class, () -> validate.validateItemRequestsId(1));
    }

    @Test
    void paginationFrom() {
        assertThrows(ValidationException.class, () -> validate.paginationFrom(-1));
    }
}