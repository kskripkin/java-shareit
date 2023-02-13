package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/requests")
public class ItemRequestController {

    private final ItemRequestService itemRequestService;

    @PostMapping
    public ItemRequestDto addRequest(@RequestBody ItemRequest itemRequest,
                                  @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("POST /requests X-Sharer-User-Id={}", userId);
        return itemRequestService.addRequest(itemRequest, userId);
    }

    @GetMapping
    public Collection<ItemRequestDto> getRequests(@RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /requests X-Sharer-User-Id={}", userId);
        return itemRequestService.getRequests(userId);
    }

    @GetMapping("/all")
    public List<ItemRequestDto> getRequestsAll(@RequestParam(required = false, defaultValue = "0") Integer from,
                                            @RequestParam(required = false, defaultValue = "10") Integer size,
                                            @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /requests/all?from={}&size={} X-Sharer-User-Id={}", from, size, userId);
        return itemRequestService.getRequestsAll(from, size, userId);
    }

    @GetMapping("/{requestId}")
    public ItemRequestDto getRequestOne(@PathVariable long requestId,
                                        @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /requests/{} X-Sharer-User-Id={}", requestId, userId);
        return itemRequestService.getRequestOne(requestId, userId);
    }

}
