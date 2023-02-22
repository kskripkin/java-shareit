package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.request.dao.ItemRequestRepository;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemRequestServiceImpl implements ItemRequestService {

    private final ItemRequestRepository itemRequestRepository;

    private final ItemRequestMapper itemRequestMapper;

    @Override
    public ItemRequestDto addRequest(ItemRequest itemRequest, long userId) {
        itemRequest.setRequesterId(userId);
        itemRequest.setCreated(LocalDateTime.now());
        return itemRequestMapper.itemRequestToItemRequestDto(itemRequestRepository.save(itemRequest));
    }

    @Override
    public Collection<ItemRequestDto> getRequests(long userId) {
        return itemRequestRepository.getItemRequests().stream()
                .map(itemRequestMapper::itemRequestToItemRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemRequestDto> getRequestsAll(int from, int size, long userId) {
        return itemRequestRepository.findByRequesterId(userId, PageRequest
                .of((from / size), size, Sort.by("created").descending()))
                .stream()
                .map(itemRequestMapper::itemRequestToItemRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public ItemRequestDto getRequestOne(long requestId, long userId) {
        return itemRequestMapper.itemRequestToItemRequestDto(itemRequestRepository.getRequestOne(requestId));
    }
}
