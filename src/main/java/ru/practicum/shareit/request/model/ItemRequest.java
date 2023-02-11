package ru.practicum.shareit.request.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "item_requests")
public class ItemRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String description;

    @Column(name = "requester_id", nullable = false)
    private Long requesterId;

    @Column(nullable = false)
    private LocalDateTime created;

    public ItemRequest(String description, long requesterId) {
        this.description = description;
        this.requesterId = requesterId;
    }
}
