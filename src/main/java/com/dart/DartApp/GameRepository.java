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

    int findGameNo() { //OK
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT MAX(gameId) as LastGame FROM Game")) {
            while (rs.next()) {
               highestGameNo = rs.getInt("LastGame");
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Error in findGameNo: " + e.getErrorCode());
        }
        return highestGameNo;
    }

    int addGame() { //OK
        LocalDate today = LocalDate.now();
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Game (typeId, gameDate, isFinished) VALUES (?,?,?)")) {
            ps.setString(1, "1");
            ps.setString(2, today.toString());
            ps.setString(3, "0");
            ps.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Error in addGame: " + e.getErrorCode());
            return 0;
        }
        return 1;
    }

    int addPPG(String nickname, int gameId) { //OK
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement("INSERT INTO PlayerPlaysGame (nickname, gameId, total) VALUES (?,?,?)")) {
            ps.setString(1, nickname);
            ps.setString(2, String.valueOf(gameId));
            ps.setString(3, "0");
            ps.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Error in addGame: " + e.getErrorCode());
            return 0;
        }
        return 1;
    }

    void reportThrow(String throwData) {
        String[] splitData = throwData.split("-");
//        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement("INSERT INTO Throw (nickname, gameId, dartNumber, target) VALUES (?,?,?,?)")) {
//            ps.setString(1, splitData[0]);
//            ps.setString(2, splitData[1]);
//            ps.setString(3, splitData[2]);
//            ps.setString(4, splitData[3]);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Error in reportThrow: " + e.getErrorCode());
//        }

    }

    void updatePPG(String gameData) { //OK
        String[] splitData = gameData.split("-");
//        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement("UPDATE PlayerPlaysGame " +
//                "SET total = (?)" +
//                "WHERE nickname = (?) AND gameId = (?)")) {
//            ps.setString(1, splitData[2]);
//            ps.setString(2, splitData[0]);
//            ps.setString(3, splitData[1]);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            //e.printStackTrace();
//            System.out.println("Error in updatePPG: " + e.getErrorCode());
//        }
    }
}