package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;

/**
 * TODO Sprint add-controllers.
 */
@Slf4j
@Service
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ItemDto addItem(@RequestBody ItemDto itemDto, @RequestHeader("X-Sharer-User-Id") int userId){
        log.info("POST /items");
        return itemService.addItem(userId, itemDto);
    }

    @PatchMapping("/{itemId}")
    public ItemDto editItem(@RequestBody ItemDto itemDto, @PathVariable int itemId, @RequestHeader("X-Sharer-User-Id") int userId){
        log.info("PATCH /items/{}", itemId);
        return itemService.editItem(userId, itemDto);
    }

    @GetMapping("/{itemId}")
    public ItemDto showItem(@PathVariable int itemId, @RequestHeader("X-Sharer-User-Id") int userId){
        log.info("GET /items/{}", itemId);
        return itemService.showItem(userId, itemId);
    }

    @GetMapping
    public Collection<ItemDto> showItems(@PathVariable int itemId, @RequestHeader("X-Sharer-User-Id") int userId){
        log.info("GET /items");
        return itemService.showItems(userId);
    }

    @GetMapping("/search?text={text}")
    public Collection<ItemDto> searchItems(@PathVariable String text, @RequestHeader("X-Sharer-User-Id") int userId){
        log.info("GET /items/search?text={}", text);
        return itemService.searchItems(userId, text);
    }
}
