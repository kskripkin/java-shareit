package ru.practicum.shareit.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bookings")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime start;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime end;

    @Column(name = "item_id", nullable = false)
    private long itemId;

    @Column(name = "booker_id", nullable = false)
    private long bookerId;

    @Enumerated(EnumType.STRING)
    private BookingState status;

    @Column(name = "item_name")
    private String itemName;
}
