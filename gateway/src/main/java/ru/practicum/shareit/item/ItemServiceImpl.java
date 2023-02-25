package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.validate.Validate;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final Validate validate;

    private final ItemClient itemClient;

    @Override
    public ResponseEntity<Object> addItem(long userId, ItemDto itemDto) {
        validate.validateLong(userId);
        validate.validateItemDto(itemDto);
        return itemClient.saveItem(userId, itemDto);
    }

    @Override
    public ResponseEntity<Object> editItem(long userId, ItemDto itemDto, long itemId) {
        validate.validateLong(userId);
        validate.validateLong(itemId);
        itemDto.setId(itemId);
        return itemClient.updateItem(userId, itemId, itemDto);
    }

    @Override
    public ResponseEntity<Object> showItem(long userId, long itemId) {
        validate.validateLong(userId);
        validate.validateLong(itemId);
        return itemClient.getItemById(userId, itemId);
    }

    @Override
    public ResponseEntity<Object> showItems(long userId) {
        validate.validateLong(userId);
        return itemClient.findItemsByUserId(userId);
    }

    @Override
    public ResponseEntity<Object> searchItems(long userId, String text) {
        validate.validateLong(userId);
        return itemClient.findByText(userId, text.toLowerCase(Locale.ROOT));
    }

    @Override
    public ResponseEntity<Object> getComments(long userId, long itemId) {
        validate.validateLong(userId);
        validate.validateLong(itemId);
        return itemClient.getCommentByItemId(userId, itemId);
    }

    @Override
    public ResponseEntity<Object> addComment(long userId, long itemId, CommentDto commentDto) {
        validate.validateLong(userId);
        validate.validateLong(itemId);
        validate.validateComment(commentDto);
        return itemClient.saveComment(userId, itemId, commentDto);
    }
}
