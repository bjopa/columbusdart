package com.dart.DartApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DartApp {

    public static void main(String[] args) {
        SpringApplication.run(DartApp.class, args);
        System.out.println(
                "\n" +
                        "\n" +
                        "   ____ ___  _    _   _ __  __ ____  _   _ ____    ____    _    ____ _____ \n" +
                        "  / ___/ _ \\| |  | | | |  \\/  | __ )| | | / ___|  |  _ \\  / \\  |  _ \\_   _|\n" +
                        " | |  | | | | |  | | | | |\\/| |  _ \\| | | \\___ \\  | | | |/ _ \\ | |_) || |  \n" +
                        " | |__| |_| | |__| |_| | |  | | |_) | |_| |___) | | |_| / ___ \\|  _ < | |  \n" +
                        "  \\____\\___/|_____\\___/|_|  |_|____/ \\___/|____/  |____/_/   \\_\\_| \\_\\|_|  \n" +
                        "                                                                           \n" +
                        "\n");
    }

}
