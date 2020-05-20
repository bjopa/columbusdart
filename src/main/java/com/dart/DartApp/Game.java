package com.dart.DartApp;

import java.util.HashMap;

public class Game {

    public int gameNo;
    public HashMap<String, Integer> playerScore = new HashMap<>();
    public HashMap<Integer, String> roundTarget = new HashMap<>();

    public Game(int gameNo, String[] players) {

        this.gameNo = gameNo;

        for (String p : players) {
            this.playerScore.put(p, 0);
        }

        this.roundTarget.put(1,"19");
        this.roundTarget.put(2,"Double");
        this.roundTarget.put(3,"18");
        this.roundTarget.put(4,"Triple");
        this.roundTarget.put(5,"17");
        this.roundTarget.put(6,"41");
        this.roundTarget.put(7,"20");
        this.roundTarget.put(8,"Bulls Eye");

    }

    public int getGameNo() {
        return gameNo;
    }

    public void setGameNo(int gameNo) {
        this.gameNo = gameNo;
    }

    public HashMap<String, Integer> getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(HashMap<String, Integer> playerScore) {
        this.playerScore = playerScore;
    }

    public HashMap<Integer, String> getRoundTarget() {
        return roundTarget;
    }

    public void setRoundTarget(HashMap<Integer, String> roundTarget) {
        this.roundTarget = roundTarget;
    }
}
