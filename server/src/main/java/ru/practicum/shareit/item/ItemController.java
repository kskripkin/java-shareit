package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Comment;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ItemDto addItem(@RequestBody ItemDto itemDto, @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("POST /items X-Sharer-User-Id={}", userId);
        return itemService.addItem(userId, itemDto);
    }

    @PatchMapping("/{itemId}")
    public ItemDto editItem(@RequestBody ItemDto itemDto, @PathVariable int itemId, @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("PATCH /items/{} X-Sharer-User-Id={}", itemId, userId);
        return itemService.editItem(userId, itemDto, itemId);
    }

    @GetMapping("/{itemId}")
    public ItemDto showItem(@PathVariable long itemId, @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /items/{} X-Sharer-User-Id={}", itemId, userId);
        return itemService.showItem(userId, itemId);
    }

    @GetMapping
    public Collection<ItemDto> showItems(@RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /items X-Sharer-User-Id={}", userId);
        return itemService.showItems(userId);
    }

    @GetMapping("/search")
    public Collection<ItemDto> searchItems(@RequestParam String text, @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /items/search?text={} X-Sharer-User-Id={}", text, userId);
        return itemService.searchItems(userId, text);
    }

    @GetMapping("/{itemId}/comment")
    public Collection<CommentDto> getCommentItems(@PathVariable long itemId, @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /items/{}/comment userId={}", itemId, userId);
        return itemService.getComments(userId, itemId);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto addComment(@PathVariable long itemId, @RequestBody Comment comment, @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("POST /items/{}/comment X-Sharer-User-Id={} comment = {}", itemId, userId, comment);
        return itemService.addComment(userId, itemId, comment);
    }
}
