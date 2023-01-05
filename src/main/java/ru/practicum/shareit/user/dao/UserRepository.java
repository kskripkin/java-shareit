package ru.practicum.shareit.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.user.model.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * " +
            "from users ", nativeQuery = true)
    List<User> getAll();

    List<User> findByEmailContainingIgnoreCase(String email);
}
