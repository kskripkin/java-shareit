package ru.practicum.shareit.request.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * TODO Sprint add-item-requests.
 */
@Data
@AllArgsConstructor
public class ItemRequestDto {
    private String description;
    private String requester;
}
