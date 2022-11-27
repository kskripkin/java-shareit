package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;

/**
 * TODO Sprint add-controllers.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/items", consumes="application/json", produces="application/json")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ItemDto addItem(@RequestBody Item item, @RequestHeader MultiValueMap<String, String> headers) {
        log.info("POST /items X-Sharer-User-Id={}", headers.getFirst("x-sharer-user-id"));
        return itemService.addItem(headers.getFirst("x-sharer-user-id"), null);
    }

    @PatchMapping("/{itemId}")
    public ItemDto editItem(@RequestBody ItemDto itemDto, @PathVariable int itemId, @RequestHeader("X-Sharer-User-Id") String userId) {
        log.info("PATCH /items/{} X-Sharer-User-Id={}", itemId, userId);
        return itemService.editItem(userId, itemDto);
    }

    @GetMapping("/{itemId}")
    public ItemDto showItem(@PathVariable int itemId, @RequestHeader("X-Sharer-User-Id") String userId) {
        log.info("GET /items/{} X-Sharer-User-Id={}", itemId, userId);
        return itemService.showItem(userId, itemId);
    }

    @GetMapping
    public Collection<ItemDto> showItems(@RequestHeader("X-Sharer-User-Id") String userId) {
        log.info("GET /items X-Sharer-User-Id={}", userId);
        return itemService.showItems(userId);
    }

    @GetMapping("/search?text={text}")
    public Collection<ItemDto> searchItems(@PathVariable String text, @RequestHeader("X-Sharer-User-Id") String userId) {
        log.info("GET /items/search?text={} X-Sharer-User-Id={}", text, userId);
        return itemService.searchItems(userId, text);
    }
}
