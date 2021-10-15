package ru.otus.lec16.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.lec16.service.author.AuthorService;
import ru.otus.lec16.util.MockEntityUtil;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Test
    @DisplayName("Добавить всех авторов в модель и отобразить во вьюхе")
    public void getAll_ShouldAddAuthorsEntriesToModelAndRenderAuthorListView() throws Exception {
        when(authorService.getAll()).thenReturn(List.of(MockEntityUtil.getAuthorPushkin(), MockEntityUtil.getAuthor()));
        mockMvc.perform(get("/author"))
                .andExpect(status().isOk())
                .andExpect(view().name("author/authors"))
                .andExpect(model().attribute("authors", hasSize(2)))
                .andExpect(model().attribute("authors", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("firstName", is("Александр")),
                                hasProperty("lastName", is("Пушкин"))
                        )
                )));
        verify(authorService, times(1)).getAll();
    }
}
