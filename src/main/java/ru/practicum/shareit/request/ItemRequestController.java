package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/requests")
public class ItemRequestController {

    private final ItemRequestService itemRequestService;

    @PostMapping
    public ItemRequest addRequest(@RequestBody ItemRequest itemRequest) {
        log.info("POST /requests");
        return itemRequestService.addRequest(itemRequest);
    }

    @GetMapping
    public Collection<ItemRequest> getRequests() {
        log.info("GET /requests");
        return itemRequestService.getRequests();
    }

    @GetMapping("/all?from={from}&size={size}")
    public Collection<ItemRequest> getRequestsAll(@RequestParam long from, @RequestParam long size) {
        log.info("GET /requests/all?from={from}&size={size}");
        return itemRequestService.getRequestsAll(from, size);
    }

    @GetMapping("/{requestId}")
    public ItemRequest getRequestOne(@PathVariable long requestId) {
        log.info("GET /requests/all?from={from}&size={size}");
        return itemRequestService.getRequestOne(requestId);
    }

}
