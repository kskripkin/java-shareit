package ru.practicum.shareit.item.dao;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ItemDAO {

    private Map<Integer, Map<Integer, Item>> userItemMap = new HashMap<>();
    private Map<Integer, Item> itemMap;

    public Item addItem(int userId, Item item){
        itemMap.put(item.getId(), item);
        userItemMap.put(userId, itemMap);
        return userItemMap.get(userId).get(item.getId());
    }

    public Item editItem(int userId, Item item){
        itemMap.put(item.getId(), item);
        userItemMap.put(userId, itemMap);
        return userItemMap.get(userId).get(item.getId());
    }

    public Item showItem(int userId, int itemId){
        return userItemMap.get(userId).get(itemId);
    }

    public Collection<Item> showItems(int userId){
        return userItemMap.get(userId).values();
    }

    public Collection<Item> searchItems(int userId, String text){
        Stream<Map.Entry<Integer, Item>> stream = userItemMap.get(userId).entrySet().stream();
        List<Item> itemList = stream.filter(x -> x.getValue().getName().contains(text) == true ||
                x.getValue().getDescription().contains(text) == true).map(x -> x.getValue()).collect(Collectors.toList());
        return itemList;
    }
}
