package ru.practicum.shareit.booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingState;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    private MockMvc mvc;

    private final ObjectMapper mapper = new ObjectMapper();

    private Booking booking;

    private BookingDto bookingDto;

    private ArrayList<BookingDto> bookingDtoArrayList;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(bookingController).build();

        booking = new Booking();
        booking.setItemName("Text");
        booking.setStatus(BookingState.APPROVED);
        booking.setBookerId(1);

        bookingDto = new BookingDto(1, LocalDateTime.now(), LocalDateTime.now().plusSeconds(10), null, BookingState.APPROVED, null);

        bookingDtoArrayList = new ArrayList<>();
        bookingDtoArrayList.add(bookingDto);
    }

    @Test
    void booking() throws Exception {
        when(bookingService.booking(1, booking)).thenReturn(bookingDto);

        mvc.perform(post("/bookings").content(mapper.writeValueAsString(booking)).header("X-Sharer-User-Id", "1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("APPROVED"))
                .andDo(print());
    }

    @Test
    void bookingApproveOrDeclined() throws Exception {
        when(bookingService.bookingApproveOrDeclined(1, true, 1)).thenReturn(bookingDto);

        mvc.perform(patch("/bookings/1?approved=true").header("X-Sharer-User-Id", "1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("APPROVED"))
                .andDo(print());
    }

    @Test
    void getBooking() throws Exception {
        when(bookingService.getBooking(1,  1)).thenReturn(bookingDto);

        mvc.perform(get("/bookings/1").header("X-Sharer-User-Id", "1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("APPROVED"))
                .andDo(print());
    }

    @Test
    void getBookingsUserAll() throws Exception {
        when(bookingService.getBookingsUserAll("ALL", 0,  1, 1)).thenReturn(bookingDtoArrayList);

        mvc.perform(get("/bookings?from=0&size=1&state=ALL").header("X-Sharer-User-Id", "1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("APPROVED"))
                .andDo(print());
    }

    @Test
    void getBookingsOwnerAll() throws Exception {
        when(bookingService.getBookingsOwnerAll("ALL", 0,  1, 1)).thenReturn(bookingDtoArrayList);

        mvc.perform(get("/bookings/owner?from=0&size=1&state=ALL").header("X-Sharer-User-Id", "1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("APPROVED"))
                .andDo(print());
    }
}