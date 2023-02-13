package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringJUnitConfig( { UserServiceImpl.class})
class UserServiceImplTest {

    private final EntityManager em;
    private final UserService service;


    @Test
    void getUser() {
    }

    @Test
    void createUser() {
    }
}