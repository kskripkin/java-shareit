package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.request.dao.ItemRequestDAO;
import ru.practicum.shareit.request.model.ItemRequest;

@Service
@RequiredArgsConstructor
public class ItemRequestServiceImpl implements ItemRequestService {

    private final ItemRequestDAO itemRequestDAO;

    @Override
    public ItemRequest addRequest(ItemRequest itemRequest) {
        return itemRequestDAO.addRequest(itemRequest);
    }
}
