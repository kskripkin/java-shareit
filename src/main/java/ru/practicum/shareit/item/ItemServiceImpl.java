package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dao.ItemDAO;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.validate.Validate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemDAO itemDAO;
    private Item item;
    private int integerUserId;
    private final Validate validate;

    @Override
    public ItemDto addItem(String userId, ItemDto itemDto) {
        validate.validateItemDto(itemDto);
        item = ItemMapper.toItem(itemDto);
        integerUserId = Integer.parseInt(userId);
        validate.validate(integerUserId);
        item = itemDAO.addItem(integerUserId, item);
        return ItemMapper.toItemDto(item);
    }

    @Override
    public ItemDto editItem(String userId, ItemDto itemDto, int itemId) {
        integerUserId = Integer.parseInt(userId);
        validate.validate(integerUserId);
        item = ItemMapper.toItem(itemDto);
        validate.validateUserOwnItem(integerUserId, itemId);
        item = itemDAO.editItem(Integer.parseInt(userId), item, itemId);
        return ItemMapper.toItemDto(item);
    }

    @Override
    public ItemDto showItem(String userId, int itemId) {
        integerUserId = Integer.parseInt(userId);
        validate.validate(integerUserId);
        return ItemMapper.toItemDto(itemDAO.showItem(itemId));
    }

    @Override
    public Collection<ItemDto> showItems(String userId) {
        Stream<Item> itemStream = itemDAO.showItems(Integer.parseInt(userId)).stream();
        return itemStream.map(x -> ItemMapper.toItemDto(x)).collect(Collectors.toList());
    }

    @Override
    public Collection<ItemDto> searchItems(String userId, String text) {
        validate.validate(integerUserId);
        if (text.equals("")) {
            return new ArrayList<>();
        }
        Stream<Item> itemStream = itemDAO.searchItems(text.toLowerCase(Locale.ROOT)).stream();
        return itemStream.map(x -> ItemMapper.toItemDto(x)).collect(Collectors.toList());
    }
}
