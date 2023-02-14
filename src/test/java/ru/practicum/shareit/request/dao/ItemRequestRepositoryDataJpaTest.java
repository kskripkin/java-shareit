package ru.practicum.shareit.request.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import ru.practicum.shareit.request.model.ItemRequest;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemRequestRepositoryDataJpaTest {

    @Autowired
    private ItemRequestRepository itemRequestRepository;

    private ItemRequest itemRequest;

    @BeforeEach
    void setUp() {
        itemRequest = new ItemRequest("Text", 1);
        itemRequest.setId(1);
        itemRequest.setItemId(1L);
        itemRequest.setCreated(LocalDateTime.now());

        itemRequestRepository.save(itemRequest);
    }

    @AfterEach
    void tearDown() {
        itemRequestRepository.deleteAll();
    }

    @Test
    void getItemRequests() {
        Collection<ItemRequest> arrayList = itemRequestRepository.getItemRequests();

        assertTrue(!arrayList.isEmpty());
    }

    @Order(1)
    @Test
    void getRequestOne() {
        ItemRequest ItemRequestFinal = itemRequestRepository.getRequestOne(1);

        assertEquals(ItemRequestFinal.getId(), itemRequest.getId());
    }

    @Test
    void findByRequesterId() {
        List<ItemRequest> arrayList = itemRequestRepository.findByRequesterId(1, PageRequest.of(0, 1));

        assertTrue(arrayList.isEmpty());
    }

    @Test
    void findById() {
        Collection<ItemRequest> arrayList = itemRequestRepository.findById(1);

        assertTrue(!arrayList.isEmpty());
    }
}