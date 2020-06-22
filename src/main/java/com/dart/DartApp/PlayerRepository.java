package com.dart.DartApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
class PlayerRepository {

    @Autowired
    private DataSource dataSource;

    private Player rsPlayer(ResultSet rs) throws SQLException {
        return new Player.Builder()
                .playerId(rs.getInt("playerId"))
                .firstName(rs.getString("firstName"))
                .lastName(rs.getString("lastName"))
                .nickname(rs.getString("nickname"))
                .bestScore(rs.getMetaData().getColumnCount() == 4 ? 0 : rs.getInt("bestScore"))
                .build();
    }

    boolean addPlayer(String firstName, String lastName, String nickname) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO dbo.\" Player\" (firstName, lastName, nickname) VALUES (?,?,?)")) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, nickname);
            ps.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getErrorCode());
            //return e.getErrorCode()==2627 ? "Nickname   \"" + nickname + "\"   not available" : "Unknown error...";
            return false;
        }
        return true;
    }

    boolean deletePlayer(String nickname) {
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM dbo.\" Player\" WHERE nickname = ?")) {
            ps.setString(1, nickname);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM dbo.\" Player\"")) {
            while (rs.next()) {
                players.add(rsPlayer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    List<Player> getTopAllTime() {
        List<Player> players = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT top(5) P.playerId, firstName, lastName, nickname, MAX(score) AS bestScore FROM dbo.\" Player\" AS P\n" +
                     "JOIN Player_Plays_Game AS PPG ON P.playerId = PPG.playerId\n" +
                     "GROUP BY P.playerId, firstName, lastName, nickname\n" +
                     "ORDER BY bestScore DESC");) {
            while (rs.next()) {
                players.add(rsPlayer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    List<Player> getTopThisYear() {
        List<Player> players = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT top(5) P.playerId, firstName, lastName, nickname, MAX(score) AS bestScore FROM dbo.[ Player] AS P\n" +
                     "JOIN Player_Plays_Game AS PPG ON P.playerId = PPG.playerId\n" +
                     "JOIN Game AS G ON PPG.gameId = G.gameId\n" +
                     "WHERE g.gameDate > '2019-12-31'\n" +
                     "GROUP BY P.playerId, firstName, lastName, nickname\n" +
                     "ORDER BY bestScore DESC")) {
            while (rs.next()) {
                players.add(rsPlayer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    List<Player> getZeros() {
        List<Player> players = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT top(5) P.playerId, firstName, lastName, nickname, COUNT(score) AS bestScore FROM dbo.[ Player] AS P\n" +
                     "JOIN Player_Plays_Game AS PPG ON P.playerId = PPG.playerId\n" +
                     "WHERE score = 0\n" +
                     "GROUP BY P.playerId, firstName, lastName, nickname\n" +
                     "ORDER BY bestScore DESC")) {
            while (rs.next()) {
                players.add(rsPlayer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }


}
