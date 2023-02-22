package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dao.CommentsRepository;
import ru.practicum.shareit.item.dao.ItemRepository;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.dao.UserRepository;
import ru.practicum.shareit.validate.Validate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;
    private final ItemMapper itemMapper;
    private final CommentMapper commentMapper;
    private Item item;
    private final Validate validate;

    @Override
    public ItemDto addItem(long userId, ItemDto itemDto) {
        validate.validateItemDto(itemDto);
        item = itemMapper.toItem(itemDto);
        item.setOwnerId(userId);
        validate.validate(userId);
        if (itemDto.getRequestId() == null) {
            item.setRequestId(0L);
        }
        return itemMapper.toItemDto(userId, itemRepository.save(item));
    }

    @Override
    public ItemDto editItem(long userId, ItemDto itemDto, long itemId) {
        validate.validate(userId);
        item = itemMapper.toItem(itemDto);
        validate.validateUserOwnItem(userId, itemId);
        item.setId(itemId);
        Item itemSource = itemRepository.getById(itemId);
        item.setOwnerId(itemSource.getOwnerId());
        if (item.getName() == null) {
            item.setName(itemSource.getName());
        }
        if (item.getDescription() == null) {
            item.setDescription(itemSource.getDescription());
        }
        if (item.getAvailable() == null) {
            item.setAvailable(itemSource.getAvailable());
        }
        if (itemDto.getRequestId() == null) {
            item.setRequestId(0L);
        }
        return itemMapper.toItemDto(userId, itemRepository.save(item));
    }

    @Override
    public ItemDto showItem(long userId, long itemId) {
        validate.validateShowItem(itemId);
        return itemMapper.toItemDto(userId, itemRepository.getById(itemId));
    }

    @Override
    public Collection<ItemDto> showItems(long userId) {
        Stream<Item> itemStream = itemRepository.findItemsByUserId(userId).stream();
        return itemStream.map(x -> itemMapper.toItemDto(userId, x)).collect(Collectors.toList());
    }

    @Override
    public Collection<ItemDto> searchItems(long userId, String text) {
        validate.validate(userId);
        if (text.equals("")) {
            return new ArrayList<>();
        }
        Stream<Item> itemStream = itemRepository.findByText(text.toLowerCase(Locale.ROOT)).stream();
        return itemStream.map(x -> itemMapper.toItemDto(userId, x)).collect(Collectors.toList());
    }

    @Override
    public Collection<CommentDto> getComments(long userId, long itemId) {
        validate.validate(userId);
        Stream<Comment> streamComment = commentsRepository.getByItemId(itemId).stream();
        return streamComment.map(x -> commentMapper.commentToCommentDto(x)).collect(Collectors.toList());
    }

    @Override
    public CommentDto addComment(long userId, long itemId, Comment comment) {
        comment.setCreated(LocalDateTime.now());
        comment.setAuthorName(userRepository.getById(userId).getName());
        comment.setItemId(itemId);
        validate.validate(userId);
        validate.validateComment(userId, comment);
        validate.validateUserBookingItem(userId, itemId);
        validate.validateBookingLastTime(userId, itemId);
        return commentMapper.commentToCommentDto(commentsRepository.save(comment));
    }
}
