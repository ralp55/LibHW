package org.example.dto;


import org.example.entity.publisher;
import org.example.util.DbUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class publisherDTO {
    private static final Logger logger = LogManager.getLogger(publisherDTO.class);


    public publisher save(publisher publisher) {
        String sql = "INSERT INTO publishers (name, head_office_city, foundation_year) " +
                "VALUES (?, ?, ?) RETURNING id";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, publisher.getName());
            stmt.setString(2, publisher.getHeadOfficeCity());
            stmt.setInt(3, publisher.getFoundationYear());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                publisher.setId(rs.getLong("id"));
                logger.info("Publisher saved successfully: " + publisher.getId());
            }
        } catch (SQLException e) {
            logger.error("Error saving publisher", e);
            throw new RuntimeException("Failed to save publisher", e);
        }
        return publisher;
    }

    public publisher findById(Long id) {
        String sql = "SELECT * FROM publishers WHERE id = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToPublisher(rs);
            }
        } catch (SQLException e) {
            logger.error("Error finding publisher by id: " + id, e);
        }
        return null;
    }

    public List<publisher> findAll() {
        String sql = "SELECT * FROM publishers ORDER BY id";
        List<publisher> publishers = new ArrayList<>();

        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                publishers.add(mapResultSetToPublisher(rs));
            }
        } catch (SQLException e) {
            logger.error("Error finding all publishers", e);
        }
        return publishers;
    }

    public publisher update(publisher publisher) {
        String sql = "UPDATE publishers SET name = ?, head_office_city = ?, " +
                "foundation_year = ? WHERE id = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, publisher.getName());
            stmt.setString(2, publisher.getHeadOfficeCity());
            stmt.setInt(3, publisher.getFoundationYear());
            stmt.setLong(4, publisher.getId());

            stmt.executeUpdate();
            logger.info("Publisher updated successfully: " + publisher.getId());
        } catch (SQLException e) {
            logger.error("Error updating publisher", e);
            throw new RuntimeException("Failed to update publisher", e);
        }
        return publisher;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM publishers WHERE id = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();
            logger.info("Publisher deleted: " + id);
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("Error deleting publisher: " + id, e);
            throw new RuntimeException("Failed to delete publisher", e);
        }
    }

    public publisher findByName(String name) {
        String sql = "SELECT * FROM publishers WHERE name = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToPublisher(rs);
            }
        } catch (SQLException e) {
            logger.error("Error finding publisher by name", e);
        }
        return null;
    }

    private publisher mapResultSetToPublisher(ResultSet rs) throws SQLException {
        publisher publisher = new publisher();
        publisher.setId(rs.getLong("id"));
        publisher.setName(rs.getString("name"));
        publisher.setHeadOfficeCity(rs.getString("head_office_city"));
        publisher.setFoundationYear(rs.getInt("foundation_year"));
        return publisher;
    }
}
