package ru.practicum.shareit.request.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.Collection;

public interface ItemRequestRepository extends JpaRepository<ItemRequest, Long> {

    @Query(value = "select * " +
            "from item_requests ", nativeQuery = true)
    Collection<ItemRequest> getItemRequests();

    @Query(value = "select * " +
            "from item_requests " +
            "where id = ?1 ", nativeQuery = true)
    ItemRequest getRequestOne(long requestId);

}
