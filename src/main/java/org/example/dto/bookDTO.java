package org.example.dto;


import org.example.entity.*;
import org.example.util.DbUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class bookDTO {
    private static final Logger logger = LogManager.getLogger(bookDTO.class);
    private authorDTO authorDTO;
    private publisherDTO publisherDTO;

    public bookDTO(authorDTO authorDTO, publisherDTO publisherDTO) {
        this.authorDTO = authorDTO;
        this.publisherDTO = publisherDTO;
    }


    public book save(book book) {
        String sql = "INSERT INTO books (title, print_year, author_id, publisher_id, created_date) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING id";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setInt(2, book.getPrintYear());
            stmt.setLong(3, book.getAuthor().getId());
            stmt.setLong(4, book.getPublisher().getId());
            stmt.setDate(5, Date.valueOf(book.getCreatedDate()));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                book.setId(rs.getLong("id"));
                logger.info("Book saved successfully: " + book.getId());
            }
        } catch (SQLException e) {
            logger.error("Error saving book", e);
            throw new RuntimeException("Failed to save book", e);
        }
        return book;
    }


    public book findById(Long id) {
        String sql = "SELECT * FROM books WHERE id = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToBook(rs);
            }
        } catch (SQLException e) {
            logger.error("Error finding book by id: " + id, e);
        }
        return null;
    }


    public List<book> findAll() {
        String sql = "SELECT * FROM books ORDER BY id";
        List<book> books = new ArrayList<>();

        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        } catch (SQLException e) {
            logger.error("Error finding all books", e);
        }
        return books;
    }


    public book update(book book) {
        String sql = "UPDATE books SET title = ?, print_year = ?, author_id = ?, " +
                "publisher_id = ?, created_date = ? WHERE id = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setInt(2, book.getPrintYear());
            stmt.setLong(3, book.getAuthor().getId());
            stmt.setLong(4, book.getPublisher().getId());
            stmt.setDate(5, Date.valueOf(book.getCreatedDate()));
            stmt.setLong(6, book.getId());

            stmt.executeUpdate();
            logger.info("Book updated successfully: " + book.getId());
        } catch (SQLException e) {
            logger.error("Error updating book", e);
            throw new RuntimeException("Failed to update book", e);
        }
        return book;
    }


    public boolean delete(Long id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();
            logger.info("Book deleted: " + id);
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("Error deleting book: " + id, e);
            throw new RuntimeException("Failed to delete book", e);
        }
    }


    public List<book> findByAuthorId(Long authorId) {
        String sql = "SELECT * FROM books WHERE author_id = ? ORDER BY id";
        List<book> books = new ArrayList<>();

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, authorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        } catch (SQLException e) {
            logger.error("Error finding books by author id", e);
        }
        return books;
    }


    public List<book> findByPublisherId(Long publisherId) {
        String sql = "SELECT * FROM books WHERE publisher_id = ? ORDER BY id";
        List<book> books = new ArrayList<>();

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, publisherId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        } catch (SQLException e) {
            logger.error("Error finding books by publisher id", e);
        }
        return books;
    }

    private book mapResultSetToBook(ResultSet rs) throws SQLException {
        book book = new book();
        book.setId(rs.getLong("id"));
        book.setTitle(rs.getString("title"));
        book.setPrintYear(rs.getInt("print_year"));

        Long authorId = rs.getLong("author_id");
        if (authorId != null) {
            book.setAuthor(authorDTO.findById(authorId));
        }

        Long publisherId = rs.getLong("publisher_id");
        if (publisherId != null) {
            book.setPublisher(publisherDTO.findById(publisherId));
        }

        Date createdDate = rs.getDate("created_date");
        if (createdDate != null) {
            book.setCreatedDate(createdDate.toLocalDate());
        }

        return book;
    }
}

