package ru.practicum.shareit.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ItemControllerTest {

    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    private MockMvc mvc;

    private final ObjectMapper mapper = new ObjectMapper();

    private Item item;

    private ItemDto itemDto;

    private Comment comment;

    private CommentDto commentDto;

    private ArrayList<CommentDto> commentDtoArrayList;

    private ArrayList<ItemDto> itemDtoArrayList;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(itemController).build();

        item = new Item("ItemName", "ItemDescription", true, 1L);
        itemDto = new ItemDto("ItemName", "ItemDescription", true, 1L);
        itemDtoArrayList = new ArrayList<>();
        itemDtoArrayList.add(itemDto);

        commentDto = new CommentDto(1, "Text", "Anna", LocalDateTime.now());
        commentDtoArrayList = new ArrayList<>();
        commentDtoArrayList.add(commentDto);

        comment = new Comment();
        comment.setAuthorName("Anna");
        comment.setText("Text");
    }

    @Test
    void addItem() throws Exception {
        when(itemService.addItem(1, itemDto)).thenReturn(itemDto);

        mvc.perform(post("/items").content(mapper.writeValueAsString(itemDto)).header("X-Sharer-User-Id", "1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(itemDto.getName())))
                .andExpect(jsonPath("$.description", is(itemDto.getDescription())));
    }

    @Test
    void editItem() throws Exception {
        when(itemService.editItem(1, itemDto, 1)).thenReturn(itemDto);

        mvc.perform(patch("/items/1").content(mapper.writeValueAsString(itemDto)).header("X-Sharer-User-Id", "1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(itemDto.getName())))
                .andExpect(jsonPath("$.description", is(itemDto.getDescription())));
    }

    @Test
    void showItem() throws Exception {
        when(itemService.showItem(1, 1)).thenReturn(itemDto);

        mvc.perform(get("/items/1").header("X-Sharer-User-Id", "1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(itemDto.getName())))
                .andExpect(jsonPath("$.description", is(itemDto.getDescription())));
    }

    @Test
    void showItems() throws Exception {
        when(itemService.showItems(1)).thenReturn(itemDtoArrayList);

        mvc.perform(get("/items").header("X-Sharer-User-Id", "1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(itemDto.getName())))
                .andExpect(jsonPath("$[0].description", is(itemDto.getDescription())));
    }

    @Test
    void searchItems() throws Exception {
        when(itemService.searchItems(1, "123")).thenReturn(itemDtoArrayList);

        mvc.perform(get("/items/search?text=123").header("X-Sharer-User-Id", "1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(itemDto.getName())))
                .andExpect(jsonPath("$[0].description", is(itemDto.getDescription())));
    }

    @Test
    void getCommentItems() throws Exception {
        when(itemService.getComments(1, 1)).thenReturn(commentDtoArrayList);

        mvc.perform(get("/items/1/comment").header("X-Sharer-User-Id", "1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].text", is(commentDto.getText())))
                .andExpect(jsonPath("$[0].authorName", is(commentDto.getAuthorName())));
    }

    @Test
    void addComment() throws Exception {
        when(itemService.addComment(1, 1, comment)).thenReturn(commentDto);

        mvc.perform(post("/items/1/comment").content(mapper.writeValueAsString(comment)).header("X-Sharer-User-Id", "1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is(comment.getText())))
                .andExpect(jsonPath("$.authorName", is(comment.getAuthorName())));
    }
}