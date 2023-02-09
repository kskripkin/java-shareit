package ru.practicum.shareit.item.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.item.model.Comment;

import java.util.ArrayList;
import java.util.Collection;

public interface CommentsRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select * " +
            "from comments ", nativeQuery = true)
    Collection<Comment> getAll();

    @Query(value = "select * " +
            "from comments " +
            "where item_id = ?1 ", nativeQuery = true)
    ArrayList<Comment> getByItemId(long itemId);

    @Query(value = "select * " +
            "from comments " +
            "where id = ?1 ", nativeQuery = true)
    Collection<Comment> getById(long id);

}
