package ru.otus.lec16.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.lec16.domain.Book;
import ru.otus.lec16.service.book.BookService;
import ru.otus.lec16.util.MockEntityUtil;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("Добавить все книги в модель и отобразить во вьюхе")
    public void getAll_ShouldAddBooksEntriesToModelAndRenderBookListView() throws Exception {
        when(bookService.getAll()).thenReturn(List.of(MockEntityUtil.getBook()));
        mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/books"))
                .andExpect(model().attribute("books", hasSize(1)))
                .andExpect(model().attribute("books", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("name", is("Java. Библиотека профессионала. Том 1. Основы")),
                                hasProperty("author", is(MockEntityUtil.getAuthor())),
                                hasProperty("genre", is(MockEntityUtil.getGenre()))
                        )
                )));
        verify(bookService, times(1)).getAll();
    }

    @Test
    @DisplayName("Добавить новую книгу в базу данных и редирект на вьюху книги")
    public void addNewBook() throws Exception {
        Book newBook = MockEntityUtil.getNewBook();
        mockMvc.perform(
                post("/book/add")
                        .param("name", newBook.getName())
                        .param("authorId", String.valueOf(newBook.getAuthor().getId()))
                        .param("genreId", String.valueOf(newBook.getGenre().getId())))
                .andExpect(redirectedUrl("/book"))
                .andExpect(status().is3xxRedirection());
    }

}
