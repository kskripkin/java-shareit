package ru.practicum.shareit.booking.config;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ConfigDateTime {

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }
}
