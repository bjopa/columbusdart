package com.dart.DartApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DartController{

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayGame playGame;

    @PostMapping("/regplayer")
    public String regPlayer(@RequestBody String sentData) {
        String[] splitOnDash = sentData
                .replaceAll("=", "")
                .replaceAll("%C3%A5", "å")
                .replaceAll("%C3%85", "Å")
                .replaceAll("%C3%A4", "ä")
                .replaceAll("%C3%84", "Ä")
                .replaceAll("%C3%B6", "ö")
                .replaceAll("%C3%96", "Ö")
                .split("-");
        for (String s : splitOnDash) {
            if (!s.matches("[a-zA-Z0-9åÅäÄöÖ]+")) return "What are you doing?\nAllowed characters are a-ö, A-Ö and 0-9!";
        }
        return playerRepository.addPlayer(splitOnDash[0], splitOnDash[1], splitOnDash[2]);
    }

    @PostMapping("/startgame")
    public void startGame(@RequestBody String[] sentData) {
        Game game = new Game(gameRepository.findGameNo() + 1, sentData);
        playGame.startGame(game);
    }

    @PostMapping("/reportThrow")
    public void reportThrow(@RequestBody int score) {
        //playGame.reportThrow(score);
    }

    @GetMapping("/getallplayers")
    public List<Player> getAllPlayers() {
        return playerRepository.getAllPlayers();
    }

    @GetMapping("/topalltime")
    public List<Player> getTopListFromAllTime() {
        return playerRepository.getTopAllTime();
    }

    @GetMapping("/topthisyear")
    public List<Player> getTopListFromThisYear() {
        return playerRepository.getTopThisYear();
    }

    @GetMapping("/zeros")
    public List<Player> getAllWhoScoredZero() {
        return playerRepository.getZeros();
    }

}
