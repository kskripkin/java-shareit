package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.request.dao.ItemRequestDAO;
import ru.practicum.shareit.request.dao.ItemRequestRepository;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ItemRequestServiceImpl implements ItemRequestService {

    private final ItemRequestRepository itemRequestRepository;

    @Override
    public ItemRequest addRequest(ItemRequest itemRequest) {
        return itemRequestRepository.save(itemRequest);
    }

    @Override
    public Collection<ItemRequest> getRequests() {
        return itemRequestRepository.getItemRequests();
    }

    @Override
    public Collection<ItemRequest> getRequestsAll(long from, long size) {
        return itemRequestRepository.getRequestsAll(from, size);
    }

    @Override
    public ItemRequest getRequestOne(long requestId) {
        return itemRequestRepository.getRequestOne(requestId);
    }
}
