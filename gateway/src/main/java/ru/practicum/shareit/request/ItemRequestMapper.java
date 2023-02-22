package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dao.ItemRepository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class ItemRequestMapper {

    private final ItemRepository itemRepository;

    public ItemRequestDto itemRequestToItemRequestDto(ItemRequest itemRequest) {
        ArrayList<Item> itemListFinal = itemRepository.getByRequestId(itemRequest.getId());
        return new ItemRequestDto(itemRequest.getId(),
                itemRequest.getDescription(),
                itemRequest.getRequesterId(),
                itemRequest.getCreated(),
                itemListFinal);
    }
}
