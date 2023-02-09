package ru.practicum.shareit.request.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ItemRequest {
    private int id;
    private String description;
    private String requester;
    private LocalDateTime created;

    public ItemRequest(String description, String requester) {
        this.description = description;
        this.requester = requester;
    }
}
