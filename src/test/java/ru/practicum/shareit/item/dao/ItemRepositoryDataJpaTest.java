package ru.practicum.shareit.item.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemRepositoryDataJpaTest {

    @Autowired
    private ItemRepository itemRepository;

    private Item item;

    @BeforeEach
    void setUp() {
        item = new Item();
        item.setAvailable(true);
        item.setName("Text");
        item.setDescription("Text");
        item.setRequestId(0L);
        item.setId(1);
        item.setOwnerId(1);

        itemRepository.save(item);
    }

    @AfterEach
    void tearDown() {
        itemRepository.deleteAll();
    }

    @Test
    void findByText() {
        List<Item> arrayList = itemRepository.findByText("Text");

        assertTrue(!arrayList.isEmpty());
    }

    @Test
    void findByUserIdAndItemId() {
        Item arrayList = itemRepository.findByUserIdAndItemId(1, 1);

        assertEquals(arrayList, item);
    }

    @Test
    void findItemsByUserId() {
        List<Item> arrayList = itemRepository.findItemsByUserId(1);

        assertTrue(!arrayList.isEmpty());
    }

    @Test
    void getAll() {
        List<Item> arrayList = itemRepository.getAll();

        assertTrue(!arrayList.isEmpty());
    }

    @Order(1)
    @Test
    void booking() {
        itemRepository.booking(1, 1);

    }

    @Test
    void getByRequestId() {
        ArrayList<Item> arrayList = itemRepository.getByRequestId(1L);

        assertTrue(!arrayList.isEmpty());
    }
}