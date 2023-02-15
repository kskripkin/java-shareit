package ru.practicum.shareit.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.model.Comment;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommentMapperTest {

    @InjectMocks
    private CommentMapper commentMapper;

    private Comment comment;

    private CommentDto commentDto;

    @BeforeEach
    void setUp() {
        comment = new Comment();
        comment.setText("Text");
        comment.setItemId(1);
        comment.setAuthorName("Text");
        comment.setCreated(LocalDateTime.now());
        comment.setId(1);

        commentDto = new CommentDto();
        commentDto.setText("Text");
        commentDto.setAuthorName("Text");
        commentDto.setCreated(LocalDateTime.now());
        commentDto.setId(1);
    }

    @Test
    void commentToCommentDto() {
        assertEquals(commentMapper.commentToCommentDto(comment), commentDto);
    }
}