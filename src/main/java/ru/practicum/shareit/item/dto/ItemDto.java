package ru.practicum.shareit.item.dto;

import lombok.Data;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class ItemDto {
    private String name;
    private String description;
    private boolean available;
    private Integer request;

    public ItemDto(String name, String description, boolean available, Integer request) {
        this.name = name;
        this.description = description;
        this.available = available;
        this.request = request;
    }

    public ItemDto(String name, String description, boolean available) {
        this.name = name;
        this.description = description;
        this.available = available;
    }
}
