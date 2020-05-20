package com.dart.DartApp;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class PlayGame {

    Game thisGame = null;
    boolean hasReported = false;

    void startGame(Game sentGame){
        this.thisGame = sentGame;
        play();
    }

    void play() {
        boolean isFinished = false;
        int numberOfPlayers = thisGame.playerScore.size();
        int roundNumber = 1, arrowsLeft;
        do {
            arrowsLeft = 3;
            do {
                //TODO vänta på nästa input
            } while (--arrowsLeft >= 1);
        } while (++roundNumber <= 8);
        isFinished = true;
    }

    String[] reportStatus() {
        String[] outData = new String[5];
        return outData;
    }

}
