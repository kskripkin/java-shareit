//package ru.practicum.shareit.booking;
//
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//import ru.practicum.shareit.booking.dto.BookingDto;
//import ru.practicum.shareit.booking.model.Booking;
//import ru.practicum.shareit.booking.model.BookingState;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//import java.time.LocalDateTime;
//
//@Transactional
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
//@SpringJUnitConfig({ BookingServiceImpl.class})
//class BookingServiceImplTest {
//
//    private final EntityManager em;
//
//    private final BookingService bookingService;
//
//    private Booking booking;
//
//    private BookingDto bookingReturnedDto;
//
////    @BeforeEach
////    void setUp() {
////        booking = new Booking();
////        booking.setId(1);
////        booking.setStart(LocalDateTime.now());
////        booking.setEnd(LocalDateTime.now().plusSeconds(10));
////        booking.setItemId(1);
////        booking.setStatus(BookingState.APPROVED);
////        booking.setItemName("Text");
////    }
//
//    @Test
//    void bookingTest() {
//        booking = new Booking();
//        booking.setId(1);
//        booking.setStart(LocalDateTime.now());
//        booking.setEnd(LocalDateTime.now().plusSeconds(10));
//        booking.setItemId(1);
//        booking.setStatus(BookingState.APPROVED);
//        booking.setItemName("Text");
//
//        bookingService.booking(1, booking);
//
////        TypedQuery<Booking> query = em.createQuery("Select u from Booking u where u.id = :id", Booking.class);
////        Booking booking1 = query.setParameter("id", 1).getSingleResult();
////
////        assertThat(booking1.getId(), notNullValue());
//    }
//
//    @Test
//    void getBooking() {
//        bookingReturnedDto = bookingService.getBooking(1, 1);
//
//        System.out.println(bookingReturnedDto);
//    }
//
//    @Test
//    void getBookingsUserAll() {
//    }
//}