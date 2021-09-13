package ru.otus.lec7.dao.book;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.lec7.domain.Author;
import ru.otus.lec7.domain.Book;
import ru.otus.lec7.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Optional<Book> findBookById(long id) {
        final String sql = "SELECT " +
                "  b.id, " +
                "  b.name, " +
                "  a.id as author_id, " +
                "  a.first_name as author_first_name, " +
                "  a.last_name as author_last_name, " +
                "  g.id as genre_id, " +
                "  g.name as genre_name " +
                "FROM " +
                "  book b " +
                "  LEFT JOIN author a ON b.author_id = a.id " +
                "  LEFT JOIN genre g ON b.genre_id = g.id " +
                "WHERE " +
                "  b.id = :id";
        try {
            return Optional.ofNullable(jdbc.queryForObject(sql,
                    Map.of("id", id),
                    new BookMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> getAll() {
        final String sql = "SELECT " +
                "  b.id, " +
                "  b.name, " +
                "  a.id as author_id, " +
                "  a.first_name as author_first_name, " +
                "  a.last_name as author_last_name, " +
                "  g.id as genre_id, " +
                "  g.name as genre_name " +
                "FROM " +
                "  book b " +
                "  LEFT JOIN author a ON b.author_id = a.id " +
                "  LEFT JOIN genre g ON b.genre_id = g.id ";
        return jdbc.query(sql, new BookMapper());
    }

    @Override
    public Long insert(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("INSERT INTO book (name, author_id, genre_id) VALUES (:name, :authorId, :genreId)", prepareParameters(book), keyHolder);
        return (long) keyHolder.getKeys().get("id");
    }

    @Override
    public void update(Book book) {
        jdbc.update("UPDATE book SET name = :name, author_id = :authorId, genre_id = :genreId WHERE id = :id", prepareParameters(book));
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update("delete from book where id = :id", Map.of("id", id));
    }

    private SqlParameterSource prepareParameters(Book book) {

        return new MapSqlParameterSource(Map.of(
                "id", book.getId(),
                "name", book.getName(),
                "authorId", book.getAuthor().getId(),
                "genreId", book.getGenre().getId())
        );
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            return Book.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .author(Author.builder()
                            .id(resultSet.getLong("author_id"))
                            .lastName(resultSet.getString("author_last_name"))
                            .firstName(resultSet.getString("author_first_name"))
                            .build())
                    .genre(Genre.builder()
                            .id(resultSet.getLong("genre_id"))
                            .name(resultSet.getString("genre_name"))
                            .build()
                    )
                    .build();
        }
    }
}
