package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.Collection;

interface ItemService {

    ItemDto addItem(String userId, ItemDto itemDto);

    ItemDto editItem(String userId, ItemDto itemDto, long itemId);

    ItemDto showItem(String userId, long itemId);

    Collection<ItemDto> showItems(String userId);

    Collection<ItemDto> searchItems(String userId, String text);

}
