package ru.practicum.shareit.request;

import org.springframework.data.domain.Page;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.Collection;
import java.util.List;

public interface ItemRequestService {

    ItemRequest addRequest(ItemRequest itemRequest, long userId);

    Collection<ItemRequest> getRequests(long userId);

    List<ItemRequest> getRequestsAll(int from, int size, long userId);

    ItemRequest getRequestOne(long requestId);
}
