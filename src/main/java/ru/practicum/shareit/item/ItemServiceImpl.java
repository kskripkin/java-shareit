package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dao.ItemDAO;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemDAO itemDAO;
    private Item item;

    @Override
    public ItemDto addItem(int userId, ItemDto itemDto) {
        item = itemDAO.addItem(userId, ItemMapper.toItem(itemDto));
        return ItemMapper.toItemDto(item);
    }

    @Override
    public ItemDto editItem(int userId, ItemDto itemDto) {
        item = itemDAO.editItem(userId, ItemMapper.toItem(itemDto));
        return ItemMapper.toItemDto(item);
    }

    @Override
    public ItemDto showItem(int userId, int itemId) {
        return ItemMapper.toItemDto(itemDAO.showItem(userId, itemId));
    }

    @Override
    public Collection<ItemDto> showItems(int userId) {
        Stream<Item> itemStream = itemDAO.showItems(userId).stream();
        return itemStream.map(x -> ItemMapper.toItemDto(x)).collect(Collectors.toList());
    }

    @Override
    public Collection<ItemDto> searchItems(int userId, String text) {
        Stream<Item> itemStream = itemDAO.searchItems(userId, text).stream();
        return itemStream.map(x -> ItemMapper.toItemDto(x)).collect(Collectors.toList());
    }
}
