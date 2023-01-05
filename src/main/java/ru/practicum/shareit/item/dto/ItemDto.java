package ru.practicum.shareit.item.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.item.model.Comment;

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

    private ArrayList<Comment> comments;

    public ItemDto(String name, String description, Boolean available) {
        this.name = name;
        this.description = description;
        this.available = available;
    }


    public ItemDto(long id, String name, String description, Boolean available, LastBooking lastBooking, NextBooking nextBooking, ArrayList<Comment> comments) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.lastBooking = lastBooking;
        this.nextBooking = nextBooking;
        this.comments = comments;
    }
}
