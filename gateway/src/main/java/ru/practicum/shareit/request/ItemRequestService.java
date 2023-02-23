package ru.practicum.shareit.request;

import org.springframework.http.ResponseEntity;
import ru.practicum.shareit.request.dto.ItemRequestDto;

public interface ItemRequestService {

    ResponseEntity<Object> addRequest(ItemRequestDto itemRequestDto, long userId);

    ResponseEntity<Object> getRequests(long userId);

    ResponseEntity<Object> getRequestsAll(int from, int size, long userId);

    ResponseEntity<Object> getRequestOne(long requestId, long user);
}
