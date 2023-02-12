package ru.practicum.shareit.request;

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
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ItemRequestControllerTest {

    @Mock
    private ItemRequestService itemRequestService;

    @InjectMocks
    private ItemRequestController itemRequestController;

    private MockMvc mvc;

    private final ObjectMapper mapper = new ObjectMapper();

    private ItemRequest itemRequest;
    private ItemRequestDto itemRequestDto;
    private ItemRequest itemRequestNew;
    private ArrayList<ItemRequestDto> itemRequestArrayList;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(itemRequestController).build();

        itemRequest = new ItemRequest("Notebook", 1L);
        itemRequest.setId(1);
        itemRequest.setRequesterId(1L);
        ArrayList<Item> itemArrayList = new ArrayList<>();
        itemRequestDto = new ItemRequestDto(1, "NotebookDto", 1L, LocalDateTime.now(), itemArrayList);
        itemRequestNew = new ItemRequest("NotebookNew", 1L);
        itemRequestArrayList = new ArrayList<>();
        itemRequestArrayList.add(itemRequestDto);
    }

    @Test
    void addRequest() throws Exception {
        when(itemRequestService.addRequest(itemRequest, 1L)).thenReturn(itemRequestDto);

        mvc.perform(post("/requests").content(mapper.writeValueAsString(itemRequest)).header("X-Sharer-User-Id", "1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(itemRequestDto.getDescription())));
    }

    @Test
    void getRequests() throws Exception {
        when(itemRequestService.getRequests(1L)).thenReturn(itemRequestArrayList);

        mvc.perform(get("/requests").header("X-Sharer-User-Id", "1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description", is(itemRequestDto.getDescription())));
    }

    @Test
    void getRequestsAll() throws Exception {
        when(itemRequestService.getRequestsAll(0, 1, 1)).thenReturn(itemRequestArrayList);

        mvc.perform(get("/requests/all?from=0&size=1").header("X-Sharer-User-Id", "1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description", is(itemRequestDto.getDescription())));
    }

    @Test
    void getRequestOne() throws Exception {
        when(itemRequestService.getRequestOne(1, 1)).thenReturn(itemRequestDto);

        mvc.perform(get("/requests/1").header("X-Sharer-User-Id", "1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(itemRequestDto.getDescription())));
    }
}