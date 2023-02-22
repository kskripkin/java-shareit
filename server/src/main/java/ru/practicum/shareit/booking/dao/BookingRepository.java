package ru.practicum.shareit.booking.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.booking.model.Booking;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "select * " +
            "from bookings ", nativeQuery = true)
    Collection<Booking> getAll();

    @Query(value = "select * " +
            "from bookings " +
            "where item_id = ?1 and " +
            "((start_time < ?2 and end_time > ?2) or " +
            "(start_time < ?3 and end_time > ?3)) ", nativeQuery = true)
    Collection<Booking> getByItemIdAndTime(long itemId, LocalDateTime startTime, LocalDateTime endTime);

    @Query(value = "select * " +
            "from bookings " +
            "where item_id = ?1 and booker_id = ?2 ", nativeQuery = true)
    Collection<Booking> getByItemIdAndUserId(long itemId, long bookerId);

    @Query(value = "select * " +
            "from bookings " +
            "where booker_id = ?1 " +
            "order by start_time desc", nativeQuery = true)
    List<Booking> getByBookerId(long userId, Pageable pageable);

    @Query(value = "select * " +
            "from bookings " +
            "where booker_id = ?1 and start_time <= ?2 and end_time > ?2 " +
            "order by start_time desc", nativeQuery = true)
    List<Booking> getByBookerIdAndCurrentTime(long userId, LocalDateTime localDateTime, Pageable pageable);

    @Query(value = "select * " +
            "from bookings " +
            "where booker_id = ?1 and end_time < ?2 " +
            "order by start_time desc", nativeQuery = true)
    List<Booking> getByBookerIdAndPastTime(long userId, LocalDateTime localDateTime, Pageable pageable);

    @Query(value = "select * " +
            "from bookings " +
            "where booker_id = ?1 and start_time > ?2 " +
            "order by start_time desc", nativeQuery = true)
    List<Booking> getByBookerIdAndFutureTime(long userId, LocalDateTime localDateTime, Pageable pageable);

    @Query(value = "select * " +
            "from bookings " +
            "where booker_id = ?1 and status = ?2 " +
            "order by start_time desc", nativeQuery = true)
    List<Booking> getByBookerIdAndStatus(long userId, String status, Pageable pageable);

    @Query(value = "select b.id, b.start_time, b.end_time, b.item_id, b.booker_id, b.status, b.item_name " +
            "from bookings as b " +
            "join items as i on b.item_id = i.id " +
            "where i.owner_id = ?1 " +
            "order by b.start_time desc", nativeQuery = true)
    List<Booking> getByOwnerId(long userId, Pageable pageable);

    @Query(value = "select b.id, b.start_time, b.end_time, b.item_id, b.booker_id, b.status, b.item_name " +
            "from bookings b " +
            "join items i on b.item_id = i.id " +
            "where i.owner_id = ?1 and b.start_time <= ?2 and b.end_time > ?2 " +
            "order by b.start_time desc", nativeQuery = true)
    List<Booking> getByOwnerIdAndCurrentTime(long userId, LocalDateTime localDateTime, Pageable pageable);

    @Query(value = "select b.id, b.start_time, b.end_time, b.item_id, b.booker_id, b.status, b.item_name " +
            "from bookings as b " +
            "join items as i on b.item_id = i.id " +
            "where i.owner_id = ?1 and b.end_time < ?2 " +
            "order by b.start_time desc", nativeQuery = true)
    List<Booking> getByOwnerIdAndPastTime(long userId, LocalDateTime localDateTime, Pageable pageable);

    @Query(value = "select b.id, b.start_time, b.end_time, b.item_id, b.booker_id, b.status, b.item_name " +
            "from bookings as b " +
            "join items as i on b.item_id = i.id " +
            "where i.owner_id = ?1 and b.start_time > ?2 " +
            "order by b.start_time desc", nativeQuery = true)
    List<Booking> getByOwnerIdAndFutureTime(long userId, LocalDateTime localDateTime, Pageable pageable);

    @Query(value = "select b.id, b.start_time, b.end_time, b.item_id, b.booker_id, b.status, b.item_name " +
            "from bookings as b " +
            "join items as i on b.item_id = i.id " +
            "where i.owner_id = ?1 and b.status = ?2 " +
            "order by b.start_time desc", nativeQuery = true)
    List<Booking> getByOwnerIdAndStatus(long userId, String status, Pageable pageable);

    @Query(value = "select b.id, b.start_time, b.end_time, b.item_id, b.booker_id, b.status, b.item_name " +
            "from bookings b " +
            "join items i on b.item_id = i.id " +
            "where b.item_id = ?1 and i.owner_id = ?3 and b.start_time <= ?2 " +
            "order by b.start_time desc " +
            "limit 1 ", nativeQuery = true)
    Booking getByItemIdLast(long itemId, LocalDateTime localDateTime, long userId);

    @Query(value = "select b.id, b.start_time, b.end_time, b.item_id, b.booker_id, b.status, b.item_name " +
            "from bookings b " +
            "join items i on b.item_id = i.id " +
            "where b.item_id = ?1 and i.owner_id = ?3 and b.start_time > ?2 " +
            "order by b.start_time " +
            "limit 1 ", nativeQuery = true)
    Booking getByItemIdNext(long itemId, LocalDateTime localDateTime, long userId);

    @Query(value = "select * " +
            "from bookings " +
            "where booker_id = ?1 and item_id = ?2 and status = 'APPROVED' ", nativeQuery = true)
    Collection<Booking> getByItemId(long bookerId, long itemId);

    @Query(value = "select * " +
            "from bookings " +
            "where booker_id = ?1 and item_id = ?2 and end_time < ?3 ", nativeQuery = true)
    Collection<Booking> getByItemIdLast(long bookerId, long itemId, LocalDateTime localDateTime);

}
