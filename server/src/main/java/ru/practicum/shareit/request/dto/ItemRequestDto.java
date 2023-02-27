package ru.practicum.shareit.request.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.item.model.Item;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class ItemRequestDto {

    private long id;

    private String description;

    private Long requesterId;

    private LocalDateTime created;

    private ArrayList<Item> items;


    public ItemRequestDto(long id, String description, Long requesterId, LocalDateTime created, ArrayList<Item> items) {
        this.id = id;
        this.description = description;
        this.requesterId = requesterId;
        this.created = created;
        this.items = items;
    }
}
