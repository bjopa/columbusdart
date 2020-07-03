package com.dart.DartApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;

@Repository
class GameRepository {

    @Autowired
    private DataSource dataSource;

    int highestGameNo = 0;

    int findGameNo() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT MAX(gameId) as LastGame FROM Game")) {
            while (rs.next()) {
               highestGameNo = rs.getInt("LastGame");
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Error: " + e.getErrorCode());
            return -1;
        }
        return highestGameNo;
    }

    int addGame() {
        LocalDate today = LocalDate.now();
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Game (typeId, gameDate) VALUES (?,?)")) {
            ps.setString(1, "1");
            ps.setString(2, today.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}