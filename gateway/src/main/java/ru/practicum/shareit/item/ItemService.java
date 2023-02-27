package ru.practicum.shareit.item;

import org.springframework.http.ResponseEntity;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;

public interface ItemService {

    ResponseEntity<Object> addItem(long userId, ItemDto itemDto);

    ResponseEntity<Object> editItem(long userId, ItemDto itemDto, long itemId);

    ResponseEntity<Object> showItem(long userId, long itemId);

    ResponseEntity<Object> showItems(long userId);

    ResponseEntity<Object> searchItems(long userId, String text);

    ResponseEntity<Object> getComments(long userId, long itemId);

    ResponseEntity<Object> addComment(long userId, long itemId, CommentDto commentDto);

}
