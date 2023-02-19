package ru.practicum.shareit.booking.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingState;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookingRepositoryDataJpaTest {

    @Autowired
    private BookingRepository bookingRepository;

    private Booking booking;

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
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getByItemIdAndTime() {
    }

    @Test
    void getByItemIdAndUserId() {
    }

    @Test
    void getByBookerId() {
    }

    @Test
    void getByBookerIdAndCurrentTime() {
    }

    @Test
    void getByBookerIdAndPastTime() {
    }

    @Test
    void getByBookerIdAndFutureTime() {
    }

    @Test
    void getByBookerIdAndStatus() {
    }

    @Test
    void getByOwnerId() {
    }

    @Test
    void getByOwnerIdAndCurrentTime() {
    }

    @Test
    void getByOwnerIdAndPastTime() {
    }

    @Test
    void getByOwnerIdAndFutureTime() {
    }

    @Test
    void getByOwnerIdAndStatus() {
    }

    @Test
    void getByItemIdLast() {
    }

    @Test
    void getByItemIdNext() {
    }

    @Test
    void getByItemId() {
    }

    @Test
    void testGetByItemIdLast() {
    }
}