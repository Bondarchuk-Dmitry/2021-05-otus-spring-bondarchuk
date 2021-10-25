package ru.otus.lec17.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.lec17.service.author.AuthorService;
import ru.otus.lec17.util.MockEntityUtil;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;


    @Test
    @DisplayName("Добавить всех авторов в модель и отобразить во вьюхе")
    public void getAll_ShouldAddAuthorsEntriesToModelAndRenderAuthorListView() throws Exception {
        when(authorService.getAll()).thenReturn(List.of(MockEntityUtil.getAuthorPushkin(), MockEntityUtil.getAuthor()));
        mockMvc.perform(get("/api/authors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Александр")))
                .andExpect(jsonPath("$[0].lastName", is("Пушкин")));
        verify(authorService, times(1)).getAll();
    }
}
