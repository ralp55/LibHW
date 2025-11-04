package org.example.dto;

import org.example.entity.author;
import org.example.util.DbUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class authorDTO {
    private static final Logger logger = LogManager.getLogger(authorDTO.class);


    public author save(author author) {
        String sql = "INSERT INTO authors (first_name, last_name, birth_city, birth_date, death_date) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING id";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, author.getFirstName());
            stmt.setString(2, author.getLastName());
            stmt.setString(3, author.getBirthCity());
            stmt.setDate(4, Date.valueOf(author.getBirthDate()));
            stmt.setDate(5, author.getDeathDate() != null ?
                    Date.valueOf(author.getDeathDate()) : null);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                author.setId(rs.getLong("id"));
                logger.info("Author saved successfully: " + author.getId());
            }
        } catch (SQLException e) {
            logger.error("Error saving author", e);
            throw new RuntimeException("Failed to save author", e);
        }
        return author;
    }


    public author findById(Long id) {
        String sql = "SELECT * FROM authors WHERE id = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToAuthor(rs);
            }
        } catch (SQLException e) {
            logger.error("Error finding author by id: " + id, e);
        }
        return null;
    }


    public List<author> findAll() {
        String sql = "SELECT * FROM authors ORDER BY id";
        List<author> authors = new ArrayList<>();

        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                authors.add(mapResultSetToAuthor(rs));
            }
        } catch (SQLException e) {
            logger.error("Error finding all authors", e);
        }
        return authors;
    }


    public author update(author author) {
        String sql = "UPDATE authors SET first_name = ?, last_name = ?, birth_city = ?, " +
                "birth_date = ?, death_date = ? WHERE id = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, author.getFirstName());
            stmt.setString(2, author.getLastName());
            stmt.setString(3, author.getBirthCity());
            stmt.setDate(4, Date.valueOf(author.getBirthDate()));
            stmt.setDate(5, author.getDeathDate() != null ?
                    Date.valueOf(author.getDeathDate()) : null);
            stmt.setLong(6, author.getId());

            stmt.executeUpdate();
            logger.info("Author updated successfully: " + author.getId());
        } catch (SQLException e) {
            logger.error("Error updating author", e);
            throw new RuntimeException("Failed to update author", e);
        }
        return author;
    }


    public boolean delete(Long id) {
        String sql = "DELETE FROM authors WHERE id = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();
            logger.info("Author deleted: " + id);
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("Error deleting author: " + id, e);
            throw new RuntimeException("Failed to delete author", e);
        }
    }


    public author findByFullName(String firstName, String lastName) {
        String sql = "SELECT * FROM authors WHERE first_name = ? AND last_name = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToAuthor(rs);
            }
        } catch (SQLException e) {
            logger.error("Error finding author by full name", e);
        }
        return null;
    }

    private author mapResultSetToAuthor(ResultSet rs) throws SQLException {
        author author = new author();
        author.setId(rs.getLong("id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));
        author.setBirthCity(rs.getString("birth_city"));

        Date birthDate = rs.getDate("birth_date");
        if (birthDate != null) {
            author.setBirthDate(birthDate.toLocalDate());
        }

        Date deathDate = rs.getDate("death_date");
        if (deathDate != null) {
            author.setDeathDate(deathDate.toLocalDate());
        }

        return author;
    }
}

