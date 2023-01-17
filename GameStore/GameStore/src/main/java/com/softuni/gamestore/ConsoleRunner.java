package com.softuni.gamestore;

import com.softuni.gamestore.Constants.Commands;
import com.softuni.gamestore.domain.services.GameService;
import com.softuni.gamestore.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.softuni.gamestore.Constants.Commands.*;

@Component
public class ConsoleRunner implements CommandLineRunner {


    private static final Scanner scanner = new Scanner(System.in);
    private UserService userService;
    private GameService gameService;

    @Autowired
    public ConsoleRunner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {

        String input = scanner.nextLine();
        while (!input.equals("close")) {

            final String[] arguments = input.split("\\|");
            final String command = arguments[0];

            final String output = switch (command) {
                case REGISTER_USER -> userService.registerUser(arguments);
                case LOGIN_USER -> userService.loginUser(arguments);
                case LOGOUT -> userService.logoutUser();
                case ADD_GAME -> gameService.addGame(arguments);
                case EDIT_GAME -> gameService.editGame(arguments);
                default -> COMMAND_NOT_FOUND;
            };
            System.out.println(output);
            input = scanner.nextLine();
        }

    }
}
