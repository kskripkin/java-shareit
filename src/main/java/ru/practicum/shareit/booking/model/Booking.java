package ru.practicum.shareit.booking.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "item_id", nullable = false)
    private long itemId;

    @Column(name = "booker_id", nullable = false)
    private long bookerId;

    @Enumerated(EnumType.STRING)
    private BookingState status;
}
