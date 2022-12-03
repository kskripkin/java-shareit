//package ru.practicum.shareit.item.dao;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import ru.practicum.shareit.booking.model.Booking;
//import ru.practicum.shareit.exception.model.NotFoundException;
//import ru.practicum.shareit.item.model.Item;
//
//import java.util.*;
//
//@Component
//@RequiredArgsConstructor
//public class ItemDAO {
//
//    private Map<Integer, Map<Integer, Item>> userItemMap = new HashMap<>();
//    private Map<Integer, Item> itemMap;
//    private int itemId = 1;
//
//    public Item addItem(int userId, Item item) {
//        item.setId(itemId++);
//        itemMap = new HashMap<>();
//        itemMap.put(item.getId(), item);
//        userItemMap.put(userId, itemMap);
//        return userItemMap.get(userId).get(item.getId());
//    }
//
//    public Item editItem(int userId, Item item, int itemId) {
//        Item sourceItem = userItemMap.get(userId).get(itemId);
//        if (item.getDescription() != null) {
//            sourceItem.setDescription(item.getDescription());
//        }
//        if (item.getName() != null) {
//            sourceItem.setName(item.getName());
//        }
//        if (item.getAvailable() != null) {
//            sourceItem.setAvailable(item.getAvailable());
//        }
//        itemMap.put(sourceItem.getId(), sourceItem);
//        userItemMap.put(userId, itemMap);
//        return userItemMap.get(userId).get(itemId);
//    }
//
//    public Item showItem(int itemId) {
//        for (Map.Entry<Integer, Map<Integer, Item>> entryUserMap : userItemMap.entrySet()) {
//            for (Map.Entry<Integer, Item> entryItemMap : entryUserMap.getValue().entrySet()) {
//                if (entryItemMap.getKey() == itemId) {
//                    return entryItemMap.getValue();
//                }
//            }
//        }
//        throw new NotFoundException("User not found");
//    }
//
//    public Collection<Item> showItems(int userId) {
//        return userItemMap.get(userId).values();
//    }
//
//    public Collection<Item> searchItems(String text) {
//        List<Item> itemList = new ArrayList<>();
//        for (Map.Entry<Integer, Map<Integer, Item>> entryUserMap : userItemMap.entrySet()) {
//            for (Map.Entry<Integer, Item> entryItemMap : entryUserMap.getValue().entrySet()) {
//                if (entryItemMap.getValue().getAvailable() == true &&
//                        (entryItemMap.getValue().getName().toLowerCase(Locale.ROOT).contains(text) ||
//                        entryItemMap.getValue().getDescription().toLowerCase(Locale.ROOT).contains(text))) {
//                    itemList.add(entryItemMap.getValue());
//                }
//            }
//        }
//        return itemList;
//    }
//
//    public Map<Integer, Map<Integer, Item>> getMapUsersAndItems() {
//        return userItemMap;
//    }
//
//    public void booking(Booking booking) {
//        for (Map.Entry<Integer, Map<Integer, Item>> entryUserMap : userItemMap.entrySet()) {
//            for (Map.Entry<Integer, Item> entryItemMap : entryUserMap.getValue().entrySet()) {
//                if (entryItemMap.getKey() == booking.getItem().getId()) {
//                    entryItemMap.getValue().setAvailable(false);
//                    entryItemMap.getValue().setRequest(booking);
//                }
//            }
//        }
//    }
//}
