package org.tj.library.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.tj.library.model.Book;

import java.util.List;

@Repository
public class BookRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookRepository.class);
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public BookRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void insertIntoBook(Book book) {
        LOGGER.info("inserting into Book table");
        final String sql = "insert into book (title, author, location) value (:title, :author, :location)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource
                .addValue("title", book.getTitle())
                .addValue("author", book.getAuthor())
                .addValue("location", book.getLocation());

        namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    public List<Book> selectAllBooks() {
        LOGGER.info("selecting all from Book");
        final String sql = "select id, title from book";

        return namedParameterJdbcTemplate.query(sql, (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));

            return book;
        });
    }

    public Book selectBook(Integer id) {
        LOGGER.info("selecting one from Book");
        final String sql = "select id, title, author, location from book where id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

        return namedParameterJdbcTemplate.query(sql, parameterSource, (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setLocation(rs.getString("location"));

            return book;
        }).get(0);
    }

    public int updateBook(Book book) {
        LOGGER.info("update book");
        final String sql = "update book " +
                " set title = :title, author = :author, location = :location " +
                " where id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource
                .addValue("id" , book.getId())
                .addValue("title", book.getTitle())
                .addValue("author", book.getAuthor())
                .addValue("location", book.getLocation());

        return namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    public int updateLocationOfBook(Integer bookId, String newLocation) {
        LOGGER.info("update book's location");
        final String sql = "update book " +
                " set location = :location " +
                " where id = :id ";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource
                .addValue("id", bookId)
                .addValue("location", newLocation);

        return namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    public int deleteBook(Integer bookId) {
        LOGGER.info("delete book");
        final String sql = "delete from book where id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", bookId);

        return namedParameterJdbcTemplate.update(sql, parameterSource);
    }
}
