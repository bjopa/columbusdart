package com.dart.DartApp;

public class Player {
    public int playerId;
    public String firstName;
    public String lastName;
    public String nickname;
    public int bestScore;

    public static class Builder {
        private int playerId;
        private String firstName;
        private String lastName;
        private String nickname;
        private int bestScore;

        Builder playerId(int playerId) {
            this.playerId = playerId;
            return this;
        }

        Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        Builder bestScore(int bestScore) {
            this.bestScore = bestScore;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }
    
    private Player(Builder builder) {
        this.playerId = builder.playerId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.nickname = builder.nickname;
        this.bestScore = builder.bestScore;
    }

}
