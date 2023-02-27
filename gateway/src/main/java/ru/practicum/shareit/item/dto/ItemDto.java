package ru.practicum.shareit.item.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class ItemDto {

    private long id;

    private String name;

    private String description;

    private Boolean available;

    private LastBooking lastBooking;

    private NextBooking nextBooking;

    private ArrayList<CommentDto> comments;

    private Long requestId;

    public ItemDto(String name, String description, Boolean available) {
        this.name = name;
        this.description = description;
        this.available = available;
    }

    public ItemDto(String name, String description, Boolean available, Long requestId) {
        this.name = name;
        this.description = description;
        this.available = available;
        this.requestId = requestId;
    }

    public ItemDto(long id, String name, String description, Boolean available, LastBooking lastBooking, NextBooking nextBooking, Long requestId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.lastBooking = lastBooking;
        this.nextBooking = nextBooking;
        this.requestId = requestId;
    }

    public ItemDto(long id, String name, String description, Boolean available, LastBooking lastBooking, NextBooking nextBooking, ArrayList<CommentDto> comments, Long requestId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.lastBooking = lastBooking;
        this.nextBooking = nextBooking;
        this.comments = comments;
        this.requestId = requestId;
    }
}
