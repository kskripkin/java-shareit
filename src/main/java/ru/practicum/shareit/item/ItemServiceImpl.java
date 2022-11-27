package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dao.ItemDAO;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.ValidateUser;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemDAO itemDAO;
    private Item item;
    private int integerUserId;
    private final ValidateUser validateUser;

    @Override
    public ItemDto addItem(String userId, ItemDto itemDto) {
        integerUserId = Integer.parseInt(userId);
        validateUser.validate(integerUserId);
        item = itemDAO.addItem(integerUserId, ItemMapper.toItem(itemDto));
        return ItemMapper.toItemDto(item);
    }

    @Override
    public ItemDto editItem(String userId, ItemDto itemDto, int itemId) {
        item = itemDAO.editItem(Integer.parseInt(userId), ItemMapper.toItem(itemDto), itemId);
        return ItemMapper.toItemDto(item);
    }

    @Override
    public ItemDto showItem(String userId, int itemId) {
        return ItemMapper.toItemDto(itemDAO.showItem(Integer.parseInt(userId), itemId));
    }

    @Override
    public Collection<ItemDto> showItems(String userId) {
        Stream<Item> itemStream = itemDAO.showItems(Integer.parseInt(userId)).stream();
        return itemStream.map(x -> ItemMapper.toItemDto(x)).collect(Collectors.toList());
    }

    @Override
    public Collection<ItemDto> searchItems(String userId, String text) {
        Stream<Item> itemStream = itemDAO.searchItems(Integer.parseInt(userId), text).stream();
        return itemStream.map(x -> ItemMapper.toItemDto(x)).collect(Collectors.toList());
    }
}
