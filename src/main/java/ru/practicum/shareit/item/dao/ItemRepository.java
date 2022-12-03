package ru.practicum.shareit.item.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "select * " +
            "from items " +
            "where name like ?1 OR description like ?1", nativeQuery = true)
    List<Item> findByText(String text);

    @Query(value = "select * " +
            "from items " +
            "where user_id = ?1 and id = ?2", nativeQuery = true)
    Item findByUserIdAndItemId(long userId, long itemId);

    @Query(value = "update items " +
            "set request_booking_id = ?1, " +
            "available = false " +
            "where id = ?2", nativeQuery = true)
    void booking(long bookId, long itemId);
}
