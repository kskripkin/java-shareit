package ru.practicum.shareit.booking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.model.Booking;

import java.time.LocalDateTime;
import java.util.Collection;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Modifying
    @Query(value = "update bookings " +
            "set status = ?1 " +
            "where id = ?2", nativeQuery = true)
    void changeStatus(String status, long id);

    @Query(value = "select * " +
            "from bookings " +
            "where booker_id = ?1 " +
            "order by start_time desc", nativeQuery = true)
    Collection<Booking> getByBookerId(long userId);

    @Query(value = "select * " +
            "from bookings " +
            "where booker_id = ?1 and start_time <= ?1 and end_time > ?1 " +
            "order by start_time desc", nativeQuery = true)
    Collection<Booking> getByBookerIdAndCurrentTime(long userId, LocalDateTime localDateTime);

    @Query(value = "select * " +
            "from bookings " +
            "where booker_id = ?1 and end_time < ?1 " +
            "order by start_time desc", nativeQuery = true)
    Collection<Booking> getByBookerIdAndPastTime(long userId, LocalDateTime localDateTime);

    @Query(value = "select * " +
            "from bookings " +
            "where booker_id = ?1 and start_time > ?1 " +
            "order by start_time desc", nativeQuery = true)
    Collection<Booking> getByBookerIdAndFutureTime(long userId, LocalDateTime localDateTime);

    @Query(value = "select * " +
            "from bookings " +
            "where booker_id = ?1 and status = ?1 " +
            "order by start_time desc", nativeQuery = true)
    Collection<Booking> getByBookerIdAndStatus(long userId, String status);


    @Query(value = "select b.id, b.start_time, b.end_time, b.item_id, b.booker_id, b.status, b.item_name " +
            "from bookings as b " +
            "join items as i on b.booker_id = i.owner_id " +
            "where i.owner_id = ?1 " +
            "order by b.start_time desc", nativeQuery = true)
    Collection<Booking> getByOwnerId(long userId);

    @Query(value = "select b.id, b.start_time, b.end_time, b.item_id, b.booker_id, b.status, b.item_name " +
            "from bookings as b " +
            "join items as i on b.booker_id = i.owner_id " +
            "where i.owner_id = ?1 and b.start_time <= ?1 b.and end_time > ?1 " +
            "order by b.start_time desc", nativeQuery = true)
    Collection<Booking> getByOwnerIdAndCurrentTime(long userId, LocalDateTime localDateTime);

    @Query(value = "select b.id, b.start_time, b.end_time, b.item_id, b.booker_id, b.status, b.item_name " +
            "from bookings as b " +
            "join items as i on b.booker_id = i.owner_id " +
            "where i.owner_id = ?1 and b.end_time < ?1 " +
            "order by b.start_time desc", nativeQuery = true)
    Collection<Booking> getByOwnerIdAndPastTime(long userId, LocalDateTime localDateTime);

    @Query(value = "select b.id, b.start_time, b.end_time, b.item_id, b.booker_id, b.status, b.item_name " +
            "from bookings as b " +
            "join items as i on b.booker_id = i.owner_id " +
            "where i.owner_id = ?1 and b.start_time > ?1 " +
            "order by b.start_time desc", nativeQuery = true)
    Collection<Booking> getByOwnerIdAndFutureTime(long userId, LocalDateTime localDateTime);

    @Query(value = "select b.id, b.start_time, b.end_time, b.item_id, b.booker_id, b.status, b.item_name " +
            "from bookings as b " +
            "join items as i on b.booker_id = i.owner_id " +
            "where i.owner_id = ?1 and b.status = ?1 " +
            "order by b.start_time desc", nativeQuery = true)
    Collection<Booking> getByOwnerIdAndStatus(long userId, String status);
}
