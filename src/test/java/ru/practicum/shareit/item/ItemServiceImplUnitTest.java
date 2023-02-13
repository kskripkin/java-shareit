package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemServiceImplUnitTest {

    private final ItemService itemService;
    private final UserService userService;
    private User user;
    private Item item;
    private ItemDto itemDto;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setName("Anna");
        user.setEmail("anna@mail.ru");
        userService.createUser(user);

        item = new Item("Text", "Text", true);
        itemDto = new ItemDto();
        itemDto.setAvailable(true);
        itemDto.setName("Text");
        itemDto.setDescription("Text");
        itemDto.setRequestId(0L);
        itemDto.setComments(new ArrayList<>());
        itemDto.setId(1);
    }

    @Order(1)
    @Test
    void addItem() {
        assertEquals(itemService.addItem(1, itemDto), itemDto);
    }

    @Order(2)
    @Test
    @Transactional
    void editItem() {
        itemDto.setName("Text2");
        assertEquals(itemService.editItem(1, itemDto, 1), itemDto);
    }

    @Order(3)
    @Test
    @Transactional
    void showItem() {
        System.out.println(itemService.showItem(1, 1));
        System.out.println(itemDto);
        assertEquals(itemService.showItem(1, 1), itemDto);
    }

    @Test
    void showItems() {
    }

    @Test
    void searchItems() {
    }

    @Test
    void getComments() {
    }

    @Test
    void addComment() {
    }
}