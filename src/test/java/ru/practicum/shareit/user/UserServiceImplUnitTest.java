package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.exception.model.NotFoundException;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceImplUnitTest {

    private User user;
    private User userTest;
    private List<User> userList;

    private final UserService userService;

    @BeforeEach
    void setUp() {
        user = new User();
        userList = new ArrayList<>();
        user.setId(1);
        user.setName("Anna");
        user.setEmail("anna@mail.ru");
        userList.add(user);
        userTest = userService.createUser(user);
    }

    @Order(2)
    @Test
    @Transactional
    void getUser() {
        assertEquals(userService.getUser(1), user);
    }

    @Order(3)
    @Test
    void getUsers() {
        assertEquals(userService.getUsers(), userList);
    }

    @Order(1)
    @Test
    void createUser() {
       assertEquals(userTest, user);
    }

    @Order(4)
    @Test
    @Transactional
    void updateUser() {
        User userUpdate = new User();
        userUpdate.setEmail("anna1@mail.ru");
        assertEquals(userService.updateUser(1, userUpdate), userUpdate);
    }

    @Order(5)
    @Test
    @Transactional
    void deleteUser() {
        userService.deleteUser(1);
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> {
            userService.getUser(1);
        }, "NotFoundException was expected");
        assertEquals(notFoundException.getMessage(), "User not found");
    }
}