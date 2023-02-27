package ru.practicum.shareit.request.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.Collection;
import java.util.List;

public interface ItemRequestRepository extends JpaRepository<ItemRequest, Long> {

    @Query(value = "select * " +
            "from item_requests ", nativeQuery = true)
    Collection<ItemRequest> getItemRequests();

    @Query(value = "select * " +
            "from item_requests " +
            "where id = ?1 ", nativeQuery = true)
    ItemRequest getRequestOne(long requestId);

    @Query(value = "select * " +
            "from item_requests " +
            "where requester_id != ?1 ", nativeQuery = true)
    List<ItemRequest> findByRequesterId(long userId, Pageable pageable);

    @Query(value = "select * " +
            "from item_requests " +
            "where id != ?1 ", nativeQuery = true)
    Collection<ItemRequest> findById(long requestId);
}
