package ru.otus.lec7.dao.book;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.lec7.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Long lastId() {
        return jdbc.getJdbcOperations().queryForObject("SELECT MAX(id) FROM book", Long.class);
    }

    @Override
    public Optional<Book> findBookById(long id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject("SELECT b.id, b.name, b.author_id, b.genre_id FROM book b WHERE b.id = :id",
                    Map.of("id", id),
                    new BookMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("SELECT b.id, b.name, b.author_id, b.genre_id FROM book b", new BookMapper());
    }

    @Override
    public void insert(Book book) {
        jdbc.update("INSERT INTO book (name, author_id, genre_id) VALUES (:name, :authorId, :genreId)", prepareParameters(book));
    }

    @Override
    public void update(Book book) {
        jdbc.update("UPDATE book SET name = :name, author_id = :authorId, genre_id = :genreId WHERE id = :id", prepareParameters(book));
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update("delete from book where id = :id", Map.of("id", id));
    }

    private Map<String, Object> prepareParameters(Book book) {
        return Map.of(
                "id", book.getId(),
                "name", book.getName(),
                "authorId", book.getAuthorId(),
                "genreId", book.getGenreId()
        );
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            return Book.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .authorId(resultSet.getLong("author_id"))
                    .genreId(resultSet.getLong("genre_id"))
                    .build();
        }
    }
}
