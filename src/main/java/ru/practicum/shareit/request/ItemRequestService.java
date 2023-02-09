package ru.practicum.shareit.request;

import ru.practicum.shareit.request.model.ItemRequest;

import java.util.Collection;

public interface ItemRequestService {

    ItemRequest addRequest(ItemRequest itemRequest);

    Collection<ItemRequest> getRequests();

    Collection<ItemRequest> getRequestsAll(long from, long size);

    ItemRequest getRequestOne(long requestId);
}
