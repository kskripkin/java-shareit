package ru.practicum.shareit.item;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.item.dao.CommentsRepository;
import ru.practicum.shareit.item.dao.ItemRepository;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.dao.UserRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.validate.Validate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplUnitTest {

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private CommentsRepository commentsRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ItemMapper itemMapper;
    @Mock
    private CommentMapper commentMapper;
    private Item item;
    private ItemDto itemDto;
    private User user;

    private Comment comment;
    private CommentDto commentDto;

    private ArrayList<Item> itemArrayList;
    private ArrayList<ItemDto> itemDtoArrayList;
    private ArrayList<Comment> commentArrayList;
    @Mock
    private Validate validate;

    @BeforeEach
    void setUp() {
        item = new Item();
        item.setAvailable(true);
        item.setName("Text");
        item.setDescription("Text");
        item.setRequestId(0L);
        item.setId(1);
        item.setOwnerId(1);

        itemDto = new ItemDto();
        itemDto.setAvailable(true);
        itemDto.setName("Text");
        itemDto.setDescription("Text");
        itemDto.setRequestId(0L);
        itemDto.setId(1);

        itemArrayList = new ArrayList<>();
        itemArrayList.add(item);

        itemDtoArrayList = new ArrayList<>();
        itemDtoArrayList.add(itemDto);

        comment = new Comment();
        comment.setText("Text");
        comment.setItemId(1);
        comment.setAuthorName("Text");
        comment.setCreated(LocalDateTime.now());
        comment.setId(1);

        commentArrayList = new ArrayList<>();
        commentArrayList.add(comment);
        commentDto = new CommentDto();
        commentDto.setText("Text");
        commentDto.setAuthorName("Text");
        commentDto.setCreated(LocalDateTime.now());
        commentDto.setId(1);

        user = new User();
        user.setId(1);

    }

    @Test
    void addItemTest() {
        when(itemMapper.toItem(any())).thenReturn(item);
        when(itemMapper.toItemDto(1, item)).thenReturn(itemDto);
        when(itemRepository.save(item)).thenReturn(item);

        ItemDto itemDtoExp = itemService.addItem(1, itemDto);

        assertEquals(itemDtoExp, itemDto);
    }

    @Test
    void editItemTest() {
        when(itemMapper.toItem(any())).thenReturn(item);
        when(itemMapper.toItemDto(1, item)).thenReturn(itemDto);
        when(itemRepository.save(item)).thenReturn(item);
        when(itemRepository.getById(1L)).thenReturn(item);

        ItemDto itemDtoExp = itemService.editItem(1, itemDto, 1);

        assertEquals(itemDtoExp, itemDto);
    }

    @Test
    void showItemTest() {
        when(itemMapper.toItemDto(1, item)).thenReturn(itemDto);
        when(itemRepository.getById(1L)).thenReturn(item);

        ItemDto itemDtoExp = itemService.showItem(1, 1);

        assertEquals(itemDtoExp, itemDto);
    }

    @Test
    void showItemsTest() {
        when(itemMapper.toItemDto(1, item)).thenReturn(itemDto);
        when(itemRepository.findItemsByUserId(1L)).thenReturn(itemArrayList);

        Collection<ItemDto> itemDtoExp = itemService.showItems(1);

        assertEquals(itemDtoExp, itemDtoArrayList);
    }

    @Test
    void searchItemsTextTest() {
        when(itemMapper.toItemDto(1, item)).thenReturn(itemDto);
        when(itemRepository.findByText(anyString())).thenReturn(itemArrayList);

        Collection<ItemDto> itemDtoExp = itemService.searchItems(1, "Text");

        assertEquals(itemDtoExp, itemDtoArrayList);
    }

    @Test
    void searchItemsEmptyTest() {

        Collection<ItemDto> itemDtoExp = itemService.searchItems(1, "");
        List<ItemDto> itemDtos = new ArrayList<>();
        assertEquals(itemDtoExp, itemDtos);
    }

    @Test
    void getCommentsTest() {
        when(commentsRepository.getByItemId(1)).thenReturn(commentArrayList);
        when(commentMapper.commentToCommentDto(comment)).thenReturn(commentDto);

        Collection<CommentDto> itemDtoExp = itemService.getComments(1, 1);
        List<CommentDto> commentDtos = new ArrayList<>();
        commentDtos.add(commentDto);

        assertEquals(itemDtoExp, commentDtos);
    }

    @Test
    void addCommentTest() {
        when(userRepository.getById(1L)).thenReturn(user);
        when(commentMapper.commentToCommentDto(comment)).thenReturn(commentDto);
        when(commentsRepository.save(comment)).thenReturn(comment);

        CommentDto commentExp = itemService.addComment(1, 1, comment);

        assertEquals(commentExp, commentDto);
    }
}