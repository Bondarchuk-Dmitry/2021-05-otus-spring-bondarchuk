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
import ru.otus.lec20.handler.author.AuthorHandlerReactive;
import ru.otus.lec20.service.author.AuthorService;
import ru.otus.lec20.util.MockEntityUtil;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest
@Import({AuthorHandlerReactive.class, AuthorRouterConfig.class})
public class AuthorRouterTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private AuthorService authorService;

    @Test
    @DisplayName("Все авторы")
    public void getAllTest() {
        when(authorService.findAll()).thenReturn(Flux.just(MockEntityUtil.getAuthor(), MockEntityUtil.getAuthorPushkin()));

        webClient.get()
                .uri("/api/authors")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$").isArray()
                .jsonPath("$[0].id").isEqualTo("61601c1540cb0c51c1187048")
                .jsonPath("$[0].firstName").isEqualTo("Кей")
                .jsonPath("$[0].lastName").isEqualTo("Хорстман");

        verify(authorService, times(1)).findAll();
    }


}
