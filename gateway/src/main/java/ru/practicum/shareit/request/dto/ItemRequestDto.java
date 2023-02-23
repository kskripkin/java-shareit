package ru.practicum.shareit.request.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ItemRequestDto {

    private long id;

    private String description;

    private Long requesterId;

    private LocalDateTime created;

//    private ArrayList<Item> items;


    public ItemRequestDto(long id, String description, Long requesterId, LocalDateTime created) {
        this.id = id;
        this.description = description;
        this.requesterId = requesterId;
        this.created = created;
    }
}
