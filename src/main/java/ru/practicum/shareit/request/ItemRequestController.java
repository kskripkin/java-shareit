package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
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
    public ItemRequest addRequest(@RequestBody ItemRequest itemRequest,
                                  @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("POST /requests X-Sharer-User-Id={}", userId);
        return itemRequestService.addRequest(itemRequest, userId);
    }

    @GetMapping
    public Collection<ItemRequest> getRequests(@RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /requests X-Sharer-User-Id={}", userId);
        return itemRequestService.getRequests(userId);
    }

    @GetMapping("/all?from={from}&size={size}")
    public List<ItemRequest> getRequestsAll(@RequestParam(required = false, defaultValue = "0") Integer from,
                                            @RequestParam(required = false, defaultValue = "10") Integer size,
                                            @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("GET /requests/all?from={}&size={} X-Sharer-User-Id={}", from, size, userId);
        return itemRequestService.getRequestsAll(from, size, userId);
    }

    @GetMapping("/{requestId}")
    public ItemRequest getRequestOne(@PathVariable long requestId) {
        log.info("GET /requests/{}", requestId);
        return itemRequestService.getRequestOne(requestId);
    }

}
