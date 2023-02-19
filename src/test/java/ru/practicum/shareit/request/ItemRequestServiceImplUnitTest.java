package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemRequestServiceImplUnitTest {

    private final ItemRequestService itemRequestService;

    private ItemRequest itemRequest;
    private ItemRequestDto itemRequestFinalDto;
    private ItemRequestDto itemRequestDto;
    private final UserService userService;
    private User user;
    private List<ItemRequestDto> itemRequestDtos;

    @BeforeEach
    void setUp() {
        itemRequest = new ItemRequest("Text", 1);
        itemRequestDto = new ItemRequestDto(1, "Text", 1L, LocalDateTime.now(), new ArrayList<>());
        user = new User();
        user.setId(1);
        user.setName("Anna");
        user.setEmail("anna@mail.ru");
        itemRequestDtos = new ArrayList<>();
        userService.createUser(user);
        itemRequestDtos.add(itemRequestDto);

        itemRequestFinalDto = itemRequestService.addRequest(itemRequest, 1);
    }

    @Order(1)
    @Test
    void addRequest() {
        assertEquals(itemRequestFinalDto.getId(), itemRequestDto.getId());
    }

    @Order(2)
    @Test
    void getRequests() {
        System.out.println(itemRequestService.getRequests(1));
        assertEquals(itemRequestService.getRequests(1).size(), 2);
    }

    @Order(3)
    @Test
    void getRequestsAll() {
        System.out.println(itemRequestService.getRequests(1));
        assertEquals(itemRequestService.getRequestsAll(0, 1, 1).size(), 0);
    }

    @Order(4)
    @Test
    void getRequestOne() {
        System.out.println(itemRequestService.getRequests(1));
    }
}