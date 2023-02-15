package ru.practicum.shareit.item.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "select * " +
            "from items " +
            "where available = true and (name ilike %?1% or description ilike %?1%)", nativeQuery = true)
    List<Item> findByText(String text);

    @Query(value = "select * " +
            "from items " +
            "where owner_id = ?1 and id = ?2", nativeQuery = true)
    Item findByUserIdAndItemId(long userId, long itemId);

    @Query(value = "select * " +
            "from items " +
            "where owner_id = ?1", nativeQuery = true)
    List<Item> findItemsByUserId(long userId);

    @Query(value = "select * " +
            "from items ", nativeQuery = true)
    List<Item> getAll();

//    @Modifying
//    @Query(value = "update items " +
//            "set request_booking_id = ?1 " +
//            "where id = ?2", nativeQuery = true)
//    void booking(long bookId, long itemId);

    @Query(value = "select * " +
            "from items " +
            "where request_id = ?1", nativeQuery = true)
    ArrayList<Item> getByRequestId(Long rqId);
}
