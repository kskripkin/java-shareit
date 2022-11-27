package ru.practicum.shareit.item.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.model.Item;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class ItemDAO {

    private Map<Integer, Map<Integer, Item>> userItemMap = new HashMap<>();
    private Map<Integer, Item> itemMap;
    private int itemId = 1;

    public Item addItem(int userId, Item item) {
        item.setId(itemId++);
        itemMap = new HashMap<>();
        itemMap.put(item.getId(), item);
        userItemMap.put(userId, itemMap);
        return userItemMap.get(userId).get(item.getId());
    }

    public Item editItem(int userId, Item item, int itemId) {
        Item sourceItem = userItemMap.get(userId).get(itemId);
        if (item.getDescription() != null) {
            sourceItem.setDescription(item.getDescription());
        }
        if (item.getName() != null) {
            sourceItem.setName(item.getName());
        }
        if (!Objects.deepEquals(item.isAvailable(), null)) {
            sourceItem.setAvailable(item.isAvailable());
        }
        itemMap.put(sourceItem.getId(), sourceItem);
        userItemMap.put(userId, itemMap);
        return userItemMap.get(userId).get(itemId);
    }

    public Item showItem(int userId, int itemId) {
        return userItemMap.get(userId).get(itemId);
    }

    public Collection<Item> showItems(int userId) {
        return userItemMap.get(userId).values();
    }

    public Collection<Item> searchItems(int userId, String text) {
        Stream<Map.Entry<Integer, Item>> stream = userItemMap.get(userId).entrySet().stream();
        List<Item> itemList = stream.filter(x -> x.getValue().getName().contains(text) == true ||
                x.getValue().getDescription().contains(text) == true).map(x -> x.getValue()).collect(Collectors.toList());
        return itemList;
    }
}
