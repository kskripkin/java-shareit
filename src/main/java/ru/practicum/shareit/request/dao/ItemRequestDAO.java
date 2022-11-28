package ru.practicum.shareit.request.dao;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class ItemRequestDAO {

    private Map<Integer, ItemRequest> itemRequestMap = new HashMap<>();
    private int id = 1;

    public ItemRequest addRequest(ItemRequest itemRequest) {
        itemRequest.setId(id++);
        itemRequestMap.put(itemRequest.getId(), itemRequest);
        return itemRequestMap.get(itemRequest.getId());
    }
}
