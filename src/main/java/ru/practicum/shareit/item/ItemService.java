package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Comment;

import java.util.Collection;

interface ItemService {

    ItemDto addItem(long userId, ItemDto itemDto);

    ItemDto editItem(long userId, ItemDto itemDto, long itemId);

    ItemDto showItem(long userId, long itemId);

    Collection<ItemDto> showItems(long userId);

    Collection<ItemDto> searchItems(long userId, String text);

    Collection<CommentDto> getComments(long userId, long itemId);

    CommentDto addComment(long userId, long itemId, Comment comment);

}
