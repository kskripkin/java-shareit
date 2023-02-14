package ru.practicum.shareit.item.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.practicum.shareit.item.model.Comment;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentsRepositoryDataJpaTest {

    @Autowired
    private CommentsRepository commentsRepository;

    private Comment comment;

    @BeforeEach
    void setUp() {
        comment = new Comment();
        comment.setText("Text");
        comment.setCreated(LocalDateTime.now());
        comment.setId(1);
        comment.setItemId(1);
        comment.setAuthorName("Text");

        commentsRepository.save(comment);
    }

    @AfterEach
    void tearDown() {
        commentsRepository.getAll();
    }

    @Test
    void getAll() {
        Collection<Comment> arrayList = commentsRepository.getAll();

        assertTrue(!arrayList.isEmpty());
    }

    @Test
    void getByItemId() {
        Collection<Comment> arrayList = commentsRepository.getByItemId(1);

        assertTrue(!arrayList.isEmpty());
    }
}