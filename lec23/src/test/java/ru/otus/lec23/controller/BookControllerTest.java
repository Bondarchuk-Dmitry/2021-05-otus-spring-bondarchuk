package ru.otus.lec23.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.lec23.domain.Book;
import ru.otus.lec23.model.RequestSaveBook;
import ru.otus.lec23.service.author.AuthorService;
import ru.otus.lec23.service.book.BookService;
import ru.otus.lec23.service.genre.GenreService;
import ru.otus.lec23.util.MockEntityUtil;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;

    @Test
    @DisplayName("Список книг не доступен не автоизованным пользователям")
    void booksShouldNotBeGrantedNoUser() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Запрет добавление новой книги для не автоизованного пользователям")
    void notAddNewBookShouldNotBeGrantedNoUser() throws Exception {
        Book newBook = MockEntityUtil.getNewBook();
        RequestSaveBook reqSaveBook = new RequestSaveBook(
                null,
                newBook.getName(),
                newBook.getAuthor().getId(),
                newBook.getGenre().getId());
        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(reqSaveBook)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Список книг")
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    public void getAllTest() throws Exception {
        when(bookService.getAll()).thenReturn(List.of(MockEntityUtil.getBook()));
        mockMvc.perform(get("/api/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Java. Библиотека профессионала. Том 1. Основы")))
                .andExpect(jsonPath("$[0].author.id", is(2)))
                .andExpect(jsonPath("$[0].author.firstName", is("Кей")))
                .andExpect(jsonPath("$[0].author.lastName", is("Хорстман")))
                .andExpect(jsonPath("$[0].genre.id", is(2)))
                .andExpect(jsonPath("$[0].genre.name", is("Нехудожественная литература")));
        verify(bookService, times(1)).getAll();
    }

    @Test
    @DisplayName("Добавить новую книгу")
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    public void saveNewBook() throws Exception {
        when(bookService.getAll()).thenReturn(List.of(MockEntityUtil.getBook(), MockEntityUtil.getNewBook()));
        Book newBook = MockEntityUtil.getNewBook();
        RequestSaveBook reqSaveBook = new RequestSaveBook(
                null,
                newBook.getName(),
                newBook.getAuthor().getId(),
                newBook.getGenre().getId());
        mockMvc.perform(
                post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(reqSaveBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("test")))
                .andExpect(jsonPath("$[1].author.id", is(1)))
                .andExpect(jsonPath("$[1].author.firstName", is("Александр")))
                .andExpect(jsonPath("$[1].author.lastName", is("Пушкин")))
                .andExpect(jsonPath("$[1].genre.id", is(1)))
                .andExpect(jsonPath("$[1].genre.name", is("Поэзия")));

        verify(bookService, times(1))
                .save(newBook.getName(),
                newBook.getAuthor().getId(),
                newBook.getGenre().getId());
    }



}
