package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<Object> addItem(@RequestBody ItemDto itemDto,
                                          @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("POST /items X-Sharer-User-Id={}", userId);
        return itemService.addItem(userId, itemDto);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Object> editItem(@RequestBody ItemDto itemDto,
                            @PathVariable int itemId,
                            @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("PATCH /items/{} X-Sharer-User-Id={}", itemId, userId);
        return itemService.editItem(userId, itemDto, itemId);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Object> showItem(@PathVariable long itemId,
                            @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /items/{} X-Sharer-User-Id={}", itemId, userId);
        return itemService.showItem(userId, itemId);
    }

    @GetMapping
    public ResponseEntity<Object> showItems(@RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /items X-Sharer-User-Id={}", userId);
        return itemService.showItems(userId);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchItems(@RequestParam String text,
                                           @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /items/search?text={} X-Sharer-User-Id={}", text, userId);
        return itemService.searchItems(userId, text);
    }

    @GetMapping("/{itemId}/comment")
    public ResponseEntity<Object> getCommentItems(@PathVariable long itemId,
                                                  @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /items/{}/comment userId={}", itemId, userId);
        return itemService.getComments(userId, itemId);
    }

    @PostMapping("/{itemId}/comment")
    public ResponseEntity<Object> addComment(@PathVariable long itemId,
                                 @RequestBody CommentDto commentDto,
                                 @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("POST /items/{}/comment X-Sharer-User-Id={} commentDto = {}", itemId, userId, commentDto);
        return itemService.addComment(userId, itemId, commentDto);
    }
}
