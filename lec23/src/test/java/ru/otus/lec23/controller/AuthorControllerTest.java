package ru.otus.lec23.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.lec23.service.author.AuthorService;
import ru.otus.lec23.util.MockEntityUtil;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Test
    @DisplayName("Список авторов не доступен не автоизованным пользователям")
    void authorsShouldNotBeGrantedNoUser() throws Exception {
        mockMvc.perform(get("/api/authors"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Добавить всех авторов в модель и отобразить во вьюхе")
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
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
