package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.request.dao.ItemRequestRepository;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.validate.Validate;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemRequestServiceImpl implements ItemRequestService {

    private final ItemRequestRepository itemRequestRepository;

    private final Validate validate;

    @Override
    public ItemRequest addRequest(ItemRequest itemRequest, long userId) {
        validate.validate(userId);
        validate.validateItemRequests(itemRequest);
        return itemRequestRepository.save(itemRequest);
    }

    @Override
    public Collection<ItemRequest> getRequests(long userId) {
        validate.validate(userId);
        return itemRequestRepository.getItemRequests();
    }

    @Override
    public List<ItemRequest> getRequestsAll(int from, int size, long userId) {
        validate.validate(userId);
        return itemRequestRepository.findByRequesterId(PageRequest.of(from, size, Sort.by("created").descending()));
    }

    @Override
    public ItemRequest getRequestOne(long requestId) {
        return itemRequestRepository.getRequestOne(requestId);
    }
}
