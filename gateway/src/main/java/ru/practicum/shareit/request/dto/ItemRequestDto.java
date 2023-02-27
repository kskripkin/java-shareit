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
}
