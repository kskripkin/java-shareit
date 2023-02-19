package ru.practicum.shareit.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.item.dao.ItemRepository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemRequestMapperTest {

    @InjectMocks
    private ItemRequestMapper itemRequestMapper;

    @Mock
    private ItemRepository itemRepository;

    @Test
    void itemRequestToItemRequestDto() {
        Item item = new Item();
        item.setAvailable(true);
        item.setName("Text");
        item.setDescription("Text");
        item.setRequestId(1L);
        item.setId(1);
        item.setOwnerId(1);
        ArrayList<Item> itemListFinal = new ArrayList<>();
        itemListFinal.add(item);
        ItemRequest itemRequest = new ItemRequest("Text", 1);
        itemRequest.setId(1);
        ItemRequestDto itemRequestDto = new ItemRequestDto(1, "Text", 1L, null, new ArrayList<>());
        itemRequestDto.setItems(itemListFinal);
        when(itemRepository.getByRequestId(1L)).thenReturn(itemListFinal);

        ItemRequestDto itemRequestDtoCheck = itemRequestMapper.itemRequestToItemRequestDto(itemRequest);

        assertEquals(itemRequestDtoCheck, itemRequestDto);
    }
}