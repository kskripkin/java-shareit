package ru.practicum.shareit.request;

import org.springframework.data.domain.Page;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.Collection;
import java.util.List;

public interface ItemRequestService {

    ItemRequestDto addRequest(ItemRequest itemRequest, long userId);

    Collection<ItemRequestDto> getRequests(long userId);

    List<ItemRequestDto> getRequestsAll(int from, int size, long userId);

    ItemRequestDto getRequestOne(long requestId, long user);
}
