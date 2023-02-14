package ru.practicum.shareit.user.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.practicum.shareit.user.model.User;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


@DataJpaTest
class UserRepositoryDataJpaTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setName("Anna");
        user.setEmail("anna@mail.ru");
        userRepository.save(user);
    }

    @AfterEach
    void deleteUser() {
        userRepository.deleteAll();
    }

    @Test
    void getAll() {
        List<User> arrayList = userRepository.getAll();

        assertTrue(!arrayList.isEmpty());
    }

    @Test
    void findByEmailContainingIgnoreCase() {
    }
}