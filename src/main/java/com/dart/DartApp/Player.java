package com.dart.DartApp;

public class Player {
    public String nickname;
    public String firstName;
    public String lastName;

    public static class Builder {
        private String nickname;
        private String firstName;
        private String lastName;

        Builder nickname(String nickname) {
            this.nickname = nickname;
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

        public Player build() {
            return new Player(this);
        }
    }
    
    private Player(Builder builder) {
        this.nickname = builder.nickname;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

}
