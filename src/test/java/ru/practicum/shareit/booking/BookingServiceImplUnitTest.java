package ru.practicum.shareit.booking;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.booking.dao.BookingRepository;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingState;
import ru.practicum.shareit.item.dao.ItemRepository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.validate.Validate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplUnitTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private Validate validate;
    @Mock
    private BookingMapper bookingMapper;

    BookingDto bookingDto;
    Booking booking;
    Item item;

    ArrayList<BookingDto> bookingDtoArrayList;
    ArrayList<Booking> bookingArrayList;

    @BeforeEach
    void setUp() {
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

        bookingDto = new BookingDto(1, ldt1, ldt2, null, BookingState.WAITING, null);
        bookingDtoArrayList = new ArrayList<>();
        bookingDtoArrayList.add(bookingDto);
        bookingArrayList = new ArrayList<>();
        bookingArrayList.add(booking);
    }

    @Test
    void bookingApproveOrDeclinedTest() {
        when(bookingRepository.getById(any())).thenReturn(booking);
        when(bookingRepository.save(booking)).thenReturn(booking);
        when(bookingMapper.toBookingDto(booking)).thenReturn(bookingDto);

        BookingDto bookingDtoExp = bookingService.bookingApproveOrDeclined(1, true, 1);

        assertEquals(bookingDtoExp, bookingDto);
    }

    @Test
    void bookingTest() {
        when(itemRepository.getById(1L)).thenReturn(item);
        when(bookingRepository.save(booking)).thenReturn(booking);
        when(bookingMapper.toBookingDto(booking)).thenReturn(bookingDto);

        BookingDto bookingDtoExp = bookingService.booking(1, booking);

        assertEquals(bookingDtoExp, bookingDto);
    }

    @Test
    void getBookingTest() {
        when(bookingRepository.getById(any())).thenReturn(booking);
        when(bookingMapper.toBookingDto(booking)).thenReturn(bookingDto);

        BookingDto bookingDtoExp = bookingService.getBooking(1, 1);

        assertEquals(bookingDtoExp, bookingDto);
    }

    @Test
    void getBookingsUserAllTest() {
        when(bookingMapper.manyToBookingDto(any())).thenReturn(bookingDtoArrayList);

        Collection<BookingDto> bdtArrALL = bookingService.getBookingsUserAll("ALL", 0, 1, 1);
        Collection<BookingDto> bdtArrCURRENT = bookingService.getBookingsUserAll("CURRENT", 0, 1, 1);
        Collection<BookingDto> bdtArrPAST = bookingService.getBookingsUserAll("PAST", 0, 1, 1);
        Collection<BookingDto> bdtArrFUTURE = bookingService.getBookingsUserAll("FUTURE", 0, 1, 1);
        Collection<BookingDto> bdtArrWAITING = bookingService.getBookingsUserAll("WAITING", 0, 1, 1);
        Collection<BookingDto> bdtArrREJECTED = bookingService.getBookingsUserAll("REJECTED", 0, 1, 1);

        assertEquals(bdtArrALL, bookingDtoArrayList);
        assertEquals(bdtArrCURRENT, bookingDtoArrayList);
        assertEquals(bdtArrPAST, bookingDtoArrayList);
        assertEquals(bdtArrFUTURE, bookingDtoArrayList);
        assertEquals(bdtArrWAITING, bookingDtoArrayList);
        assertEquals(bdtArrREJECTED, bookingDtoArrayList);
    }

    @Test
    void getBookingsOwnerAllTest() {
        when(bookingMapper.manyToBookingDto(any())).thenReturn(bookingDtoArrayList);

        Collection<BookingDto> bdtArrALL = bookingService.getBookingsOwnerAll("ALL", 0, 1, 1);
        Collection<BookingDto> bdtArrCURRENT = bookingService.getBookingsOwnerAll("CURRENT", 0, 1, 1);
        Collection<BookingDto> bdtArrPAST = bookingService.getBookingsOwnerAll("PAST", 0, 1, 1);
        Collection<BookingDto> bdtArrFUTURE = bookingService.getBookingsOwnerAll("FUTURE", 0, 1, 1);
        Collection<BookingDto> bdtArrWAITING = bookingService.getBookingsOwnerAll("WAITING", 0, 1, 1);
        Collection<BookingDto> bdtArrREJECTED = bookingService.getBookingsOwnerAll("REJECTED", 0, 1, 1);

        assertEquals(bdtArrALL, bookingDtoArrayList);
        assertEquals(bdtArrCURRENT, bookingDtoArrayList);
        assertEquals(bdtArrPAST, bookingDtoArrayList);
        assertEquals(bdtArrFUTURE, bookingDtoArrayList);
        assertEquals(bdtArrWAITING, bookingDtoArrayList);
        assertEquals(bdtArrREJECTED, bookingDtoArrayList);
    }
}