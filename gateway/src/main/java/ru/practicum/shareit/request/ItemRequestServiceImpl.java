package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.validate.Validate;

@Service
@RequiredArgsConstructor
public class ItemRequestServiceImpl implements ItemRequestService {


    private final Validate validate;

    private final ItemRequestClient itemRequestClient;


    @Override
    public ResponseEntity<Object> addRequest(ItemRequestDto itemRequestDto, long userId) {
        validate.validateLong(userId);
        validate.validateItemRequests(itemRequestDto);
        return itemRequestClient.saveItemRequest(userId, itemRequestDto);
    }

    @Override
    public ResponseEntity<Object> getRequests(long userId) {
        validate.validateLong(userId);
        return itemRequestClient.getItemRequests(userId);
    }

    @Override
    public ResponseEntity<Object> getRequestsAll(int from, int size, long userId) {
        validate.validateLong(userId);
        validate.paginationFrom(from);
        validate.paginationFrom(size);
        return itemRequestClient.findByRequesterId(userId, size, from);
    }

    @Override
    public ResponseEntity<Object> getRequestOne(long requestId, long userId) {
        validate.validateLong(userId);
        validate.validateLong(requestId);
        return itemRequestClient.getRequestOne(userId, requestId);
    }
}
