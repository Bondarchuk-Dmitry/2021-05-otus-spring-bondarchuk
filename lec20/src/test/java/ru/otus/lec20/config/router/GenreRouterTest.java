package ru.otus.lec20.config.router;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.otus.lec20.handler.genre.GenreHandlerReactive;
import ru.otus.lec20.service.genre.GenreService;
import ru.otus.lec20.util.MockEntityUtil;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest
@Import({GenreRouterConfig.class, GenreHandlerReactive.class})
public class GenreRouterTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private GenreService genreService;

    @Test
    @DisplayName("Все авторы")
    public void getAllTest() {
        when(genreService.findAll()).thenReturn(Flux.just(MockEntityUtil.getGenrePoetry(), MockEntityUtil.getGenre()));

        webClient.get()
                .uri("/api/genres")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$").isArray()
                .jsonPath("$[0].id").isEqualTo("61601c6f6592350e22a07fbd")
                .jsonPath("$[0].name").isEqualTo("Поэзия");

        verify(genreService, times(1)).findAll();
    }

}
