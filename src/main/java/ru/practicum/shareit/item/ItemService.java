package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.Collection;

interface ItemService {

    ItemDto addItem(int userId, ItemDto itemDto);

    ItemDto editItem(int userId, ItemDto itemDto);

    ItemDto showItem(int userId, int itemId);

    Collection<ItemDto> showItems(int userId);

    Collection<ItemDto> searchItems(int userId, String text);

}
